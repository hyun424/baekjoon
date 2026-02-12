"""
매일 09:00 KST 복습 알림 발송.

- review_data.json에서 next_review <= 오늘인 문제 필터
- Discord embed + 버튼 메시지 발송 (5문제씩 분할)
- 복습할 문제 없으면 "오늘은 쉬는 날!" 메시지
"""

import json
import os
import sys
import time
import urllib.request
import urllib.error
from datetime import datetime, timedelta, timezone

sys.path.insert(0, os.path.dirname(__file__))
from sm2 import INTERVALS

BATCH_SIZE = 5  # Discord 메시지당 최대 action row 수


def load_review_data(path: str) -> dict:
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def get_due_problems(data: dict) -> list[dict]:
    """오늘 복습해야 할 문제 목록 반환 (graduated 제외)."""
    today = datetime.now(timezone.utc).date().isoformat()
    due = []
    for pid, problem in data["problems"].items():
        sm2 = problem.get("sm2", {})
        if sm2.get("graduated", False):
            continue
        next_review = sm2.get("next_review")
        if next_review and next_review <= today:
            due.append(problem)
    # 오래된 것부터
    due.sort(key=lambda p: p["sm2"].get("next_review", ""))
    return due


def _build_problem_block(problem: dict, index: int):
    """문제 1개의 embed 텍스트 + action row 반환."""
    sm2 = problem["sm2"]
    review_count = sm2.get("review_count", 0)
    last_reviewed = sm2.get("last_reviewed")

    if last_reviewed:
        days_ago = (datetime.now(timezone.utc).date() - datetime.strptime(last_reviewed, "%Y-%m-%d").date()).days
        last_info = f"마지막 복습: {days_ago}일 전"
    else:
        last_info = "첫 복습!"

    category_str = ", ".join(problem.get("category", [])) or "미분류"
    step = sm2.get("step", 0)
    interval_info = f"단계: {step + 1}/{len(INTERVALS)} ({INTERVALS[min(step, len(INTERVALS)-1)]}일)"

    text = f"\n\n**{index}. [{problem['difficulty']}] {problem['title']} - {problem['number']}**"
    text += f"\n   분류: {category_str}"
    text += f"\n   {last_info} | 복습 횟수: {review_count}회 | {interval_info}"
    text += f"\n   🔗 [문제 링크]({problem['link']})"

    pid = problem["id"]
    action_row = {
        "type": 1,
        "components": [
            {"type": 2, "style": 4, "label": "😰 모름", "custom_id": f"review_{pid}_1"},
            {"type": 2, "style": 1, "label": "🤔 어렴풋이", "custom_id": f"review_{pid}_3"},
            {"type": 2, "style": 3, "label": "😊 기억남", "custom_id": f"review_{pid}_5"},
        ]
    }
    return text, action_row


def build_discord_payloads(due_problems: list[dict], stats: dict) -> list[dict]:
    """Discord API용 메시지 페이로드 리스트 생성 (5문제씩 분할)."""
    footer_base = f"📊 총 {stats.get('total_problems', 0)}문제 | {stats.get('total_reviews', 0)}회 복습 | 🔥 {stats.get('current_streak', 0)}일 연속"

    if not due_problems:
        return [{
            "embeds": [{
                "title": "📚 오늘의 복습",
                "description": "🎉 오늘은 쉬는 날! 복습할 문제가 없습니다.",
                "color": 0x2ECC71,
                "footer": {"text": footer_base},
                "timestamp": datetime.now(timezone.utc).isoformat(),
            }]
        }]

    total = len(due_problems)
    batches = [due_problems[i:i + BATCH_SIZE] for i in range(0, total, BATCH_SIZE)]
    payloads = []

    for batch_idx, batch in enumerate(batches):
        embed_desc = ""
        components = []

        if batch_idx == 0:
            embed_desc = f"**📚 오늘의 복습 문제 ({total}문제)**\n━━━━━━━━━━━━━━━"

        for j, problem in enumerate(batch):
            global_index = batch_idx * BATCH_SIZE + j + 1
            text, action_row = _build_problem_block(problem, global_index)
            embed_desc += text
            components.append(action_row)

        footer_text = footer_base
        if batch_idx < len(batches) - 1:
            remaining = total - (batch_idx + 1) * BATCH_SIZE
            footer_text = f"⬇️ {remaining}문제 더 있음 | " + footer_base

        payload = {
            "embeds": [{
                "description": embed_desc,
                "color": 0x3498DB,
                "footer": {"text": footer_text},
                "timestamp": datetime.now(timezone.utc).isoformat(),
            }],
            "components": components,
        }
        payloads.append(payload)

    return payloads


def send_discord_message(bot_token: str, channel_id: str, payload: dict):
    """Discord Bot API로 메시지 발송."""
    url = f"https://discord.com/api/v10/channels/{channel_id}/messages"
    headers = {
        "Authorization": f"Bot {bot_token}",
        "Content-Type": "application/json",
        "User-Agent": "ReviewBot/1.0",
    }
    body = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(url, data=body, headers=headers, method="POST")

    try:
        with urllib.request.urlopen(req) as resp:
            print(f"Discord message sent! Status: {resp.status}")
    except urllib.error.HTTPError as e:
        print(f"Discord API error: {e.code}")
        print(e.read().decode())
        sys.exit(1)


def main():
    data_path = sys.argv[1] if len(sys.argv) > 1 else "review_data.json"
    data = load_review_data(data_path)

    due = get_due_problems(data)
    print(f"Due problems today: {len(due)}")
    for p in due:
        print(f"  - {p['id']}: {p['title']}")

    payloads = build_discord_payloads(due, data["stats"])
    print(f"Messages to send: {len(payloads)}")

    bot_token = os.environ.get("DISCORD_BOT_TOKEN")
    channel_id = os.environ.get("DISCORD_CHANNEL_ID")

    if bot_token and channel_id:
        for i, payload in enumerate(payloads):
            if i > 0:
                time.sleep(1)  # rate limit 방지
            send_discord_message(bot_token, channel_id, payload)
    else:
        print("DISCORD_BOT_TOKEN or DISCORD_CHANNEL_ID not set. Printing payload:")
        print(json.dumps(payloads[0], ensure_ascii=False, indent=2))
        if len(payloads) > 1:
            print(f"... and {len(payloads) - 1} more messages")


if __name__ == "__main__":
    main()
