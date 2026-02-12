"""
Discord 버튼 피드백 → SM-2 업데이트.

repository_dispatch의 client_payload에서:
  - problem_id: 문제 ID (e.g., "baekjoon_1074")
  - quality: 기억도 (1, 3, 5)
"""

import json
import os
import sys
from datetime import datetime, timedelta, timezone

sys.path.insert(0, os.path.dirname(__file__))
from sm2 import update_sm2, get_interval_label
from register_problem import load_review_data, save_review_data


def process_feedback(data: dict, problem_id: str, quality: int) -> str | None:
    """피드백 처리. 결과 메시지 반환."""
    if problem_id not in data["problems"]:
        return None

    problem = data["problems"][problem_id]
    old_sm2 = problem["sm2"]
    new_sm2 = update_sm2(old_sm2, quality)
    problem["sm2"] = new_sm2

    # 통계 업데이트
    data["stats"]["total_reviews"] = data["stats"].get("total_reviews", 0) + 1

    # 연속 기록
    today = datetime.now(timezone.utc).date().isoformat()
    stats = data["stats"]
    last_date = stats.get("last_review_date")
    yesterday = (datetime.now(timezone.utc).date() - timedelta(days=1)).isoformat()

    if last_date != today:
        if last_date == yesterday:
            stats["current_streak"] = stats.get("current_streak", 0) + 1
        else:
            stats["current_streak"] = 1
        stats["longest_streak"] = max(stats.get("longest_streak", 0), stats["current_streak"])
        stats["last_review_date"] = today

    # 결과 메시지
    if new_sm2["graduated"]:
        return f"🎓 {problem['title']} 복습 완료! 더 이상 알림이 오지 않습니다."
    else:
        next_review = new_sm2["next_review"]
        interval = new_sm2["interval"]
        step = new_sm2["step"]
        return f"📝 {problem['title']} → 단계 {step + 1}/5, 다음 복습: {next_review} ({interval}일 후)"


def main():
    problem_id = os.environ.get("PROBLEM_ID", "")
    quality = int(os.environ.get("QUALITY", "0"))
    data_path = sys.argv[1] if len(sys.argv) > 1 else "review_data.json"

    if not problem_id or quality not in (1, 3, 5):
        print(f"ERROR: Invalid input - problem_id={problem_id}, quality={quality}")
        sys.exit(1)

    data = load_review_data(data_path)
    result = process_feedback(data, problem_id, quality)

    if result is None:
        print(f"ERROR: Problem not found - {problem_id}")
        sys.exit(1)

    save_review_data(data_path, data)
    print(result)


if __name__ == "__main__":
    main()
