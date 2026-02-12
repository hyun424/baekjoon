"""
매일 09:00 KST 복습 알림 발송.

- review_data.json에서 next_review <= 오늘인 문제 필터
- Discord embed + 버튼 메시지 발송
- 복습할 문제 없으면 "오늘은 쉬는 날!" 메시지
"""

import json
import os
import sys
import urllib.request
import urllib.error
from datetime import datetime, timedelta, timezone

sys.path.insert(0, os.path.dirname(__file__))
from sm2 import INTERVALS


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


def build_discord_message(due_problems: list[dict], stats: dict) -> dict:
    """Discord API용 메시지 페이로드 생성."""
    today_str = datetime.now(timezone.utc).strftime("%Y-%m-%d")

    if not due_problems:
        return {
            "embeds": [{
                "title": "📚 오늘의 복습",
                "description": "🎉 오늘은 쉬는 날! 복습할 문제가 없습니다.",
                "color": 0x2ECC71,
                "footer": {"text": f"📊 총 {stats.get('total_problems', 0)}문제 | {stats.get('total_reviews', 0)}회 복습 | 🔥 {stats.get('current_streak', 0)}일 연속"},
                "timestamp": datetime.now(timezone.utc).isoformat(),
            }]
        }

    # Discord는 메시지당 최대 5개 action row (각 5개 버튼)
    # 문제당 1개 action row (3개 버튼) → 최대 5문제/메시지
    # 5문제 이상이면 여러 메시지로 분할
    embeds = []
    components = []

    embed_desc = f"**📚 오늘의 복습 문제 ({len(due_problems)}문제)**\n━━━━━━━━━━━━━━━"

    for i, problem in enumerate(due_problems[:5]):  # 첫 5개만 (Discord 제한)
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
        interval_info = f"단계: {step + 1}/5 ({INTERVALS[min(step, len(INTERVALS)-1)]}일)"

        embed_desc += f"\n\n**{i+1}. [{problem['difficulty']}] {problem['title']} - {problem['number']}**"
        embed_desc += f"\n   분류: {category_str}"
        embed_desc += f"\n   {last_info} | 복습 횟수: {review_count}회 | {interval_info}"
        embed_desc += f"\n   🔗 [문제 링크]({problem['link']})"

        # 버튼 action row
        pid = problem["id"]
        components.append({
            "type": 1,  # ACTION_ROW
            "components": [
                {
                    "type": 2,  # BUTTON
                    "style": 4,  # DANGER (red)
                    "label": "😰 모름",
                    "custom_id": f"review_{pid}_1",
                },
                {
                    "type": 2,
                    "style": 1,  # PRIMARY (blue)
                    "label": "🤔 어렴풋이",
                    "custom_id": f"review_{pid}_3",
                },
                {
                    "type": 2,
                    "style": 3,  # SUCCESS (green)
                    "label": "😊 기억남",
                    "custom_id": f"review_{pid}_5",
                },
            ]
        })

    footer_text = f"📊 총 {stats.get('total_problems', 0)}문제 | {stats.get('total_reviews', 0)}회 복습 | 🔥 {stats.get('current_streak', 0)}일 연속"
    if len(due_problems) > 5:
        footer_text = f"⚠️ {len(due_problems) - 5}문제 더 있음 | " + footer_text

    embeds.append({
        "description": embed_desc,
        "color": 0x3498DB,
        "footer": {"text": footer_text},
        "timestamp": datetime.now(timezone.utc).isoformat(),
    })

    return {"embeds": embeds, "components": components}


def send_discord_message(webhook_url: str = None, bot_token: str = None,
                         channel_id: str = None, payload: dict = None):
    """Discord로 메시지 발송. bot_token+channel_id 우선, 없으면 webhook."""
    if bot_token and channel_id:
        url = f"https://discord.com/api/v10/channels/{channel_id}/messages"
        headers = {
            "Authorization": f"Bot {bot_token}",
            "Content-Type": "application/json",
        }
    elif webhook_url:
        url = webhook_url
        headers = {"Content-Type": "application/json"}
    else:
        print("ERROR: No Discord credentials provided")
        sys.exit(1)

    body = json.dumps(payload).encode("utf-8")
    req = urllib.request.Request(url, data=body, headers=headers, method="POST")

    try:
        with urllib.request.urlopen(req) as resp:
            print(f"Discord message sent! Status: {resp.status}")
    except urllib.error.HTTPError as e:
        print(f"Discord API error: {e.code}")
        print(e.read().decode())
        sys.exit(1)


def update_streak(data: dict):
    """연속 복습 기록 업데이트."""
    today = datetime.now(timezone.utc).date().isoformat()
    stats = data["stats"]
    last_date = stats.get("last_review_date")

    if last_date == today:
        return  # 이미 오늘 업데이트됨

    yesterday = (datetime.now(timezone.utc).date() - timedelta(days=1)).isoformat()
    if last_date == yesterday:
        stats["current_streak"] = stats.get("current_streak", 0) + 1
    else:
        stats["current_streak"] = 1

    stats["longest_streak"] = max(stats.get("longest_streak", 0), stats["current_streak"])
    stats["last_review_date"] = today


def main():
    data_path = sys.argv[1] if len(sys.argv) > 1 else "review_data.json"
    data = load_review_data(data_path)

    due = get_due_problems(data)
    print(f"Due problems today: {len(due)}")
    for p in due:
        print(f"  - {p['id']}: {p['title']}")

    payload = build_discord_message(due, data["stats"])

    bot_token = os.environ.get("DISCORD_BOT_TOKEN")
    channel_id = os.environ.get("DISCORD_CHANNEL_ID")

    if bot_token and channel_id:
        send_discord_message(bot_token=bot_token, channel_id=channel_id, payload=payload)
    else:
        print("DISCORD_BOT_TOKEN or DISCORD_CHANNEL_ID not set. Printing payload:")
        print(json.dumps(payload, ensure_ascii=False, indent=2))


if __name__ == "__main__":
    main()
