"""
SM-2 기반 망각곡선 복습 스케줄러 (고정 간격 변형)

기억도 3단계:
  1 (모름/😰)     → 1단계(1일)로 리셋
  3 (어렴풋이/🤔) → 같은 단계 유지
  5 (기억남/😊)   → 다음 단계로 진행

고정 간격: 1일 → 3일 → 7일 → 14일 → 21일
5단계 모두 통과 → 복습 완료 (graduated)
"""

from datetime import datetime, timedelta, timezone

INTERVALS = [1, 3, 7, 14, 21]
MAX_STEP = len(INTERVALS) - 1


def create_initial_sm2(first_solved: str) -> dict:
    """새 문제 등록 시 초기 SM-2 상태 생성."""
    solved_date = datetime.strptime(first_solved, "%Y-%m-%d").date()
    next_review = solved_date + timedelta(days=INTERVALS[0])
    return {
        "step": 0,
        "interval": INTERVALS[0],
        "last_reviewed": None,
        "next_review": next_review.isoformat(),
        "review_count": 0,
        "graduated": False,
        "history": [],
    }


def update_sm2(sm2: dict, quality: int) -> dict:
    """
    복습 피드백 반영.

    Args:
        sm2: 현재 SM-2 상태
        quality: 1(모름), 3(어렴풋이), 5(기억남)

    Returns:
        업데이트된 SM-2 상태
    """
    today = datetime.now(timezone.utc).date().isoformat()
    step = sm2.get("step", 0)

    if quality == 5:
        # 기억남 → 다음 단계
        new_step = min(step + 1, MAX_STEP + 1)
    elif quality == 3:
        # 어렴풋이 → 같은 단계 유지
        new_step = step
    else:
        # 모름 → 1단계로 리셋
        new_step = 0

    graduated = new_step > MAX_STEP
    if graduated:
        new_interval = 0
        next_review = None
    else:
        new_interval = INTERVALS[new_step]
        next_date = datetime.now(timezone.utc).date() + timedelta(days=new_interval)
        next_review = next_date.isoformat()

    history_entry = {
        "date": today,
        "quality": quality,
        "interval_after": new_interval,
    }

    return {
        "step": new_step if not graduated else MAX_STEP + 1,
        "interval": new_interval,
        "last_reviewed": today,
        "next_review": next_review,
        "review_count": sm2.get("review_count", 0) + 1,
        "graduated": graduated,
        "history": sm2.get("history", []) + [history_entry],
    }


def get_interval_label(step: int) -> str:
    """현재 단계의 간격을 사람이 읽을 수 있는 형태로 반환."""
    if step > MAX_STEP:
        return "복습 완료"
    return f"{INTERVALS[step]}일"
