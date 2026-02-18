"""
README.md 파싱 → review_data.json에 문제 등록.

백준 README 형식:
  # [Gold V] Z - 1074
  [문제 링크](https://www.acmicpc.net/problem/1074)
  ### 분류
  분할 정복, 재귀
  ### 제출 일자
  2025년 9월 4일 18:15:49

프로그래머스 README 형식:
  # [level 2] 택배 배달과 수거하기 - 150369
  [문제 링크](https://school.programmers.co.kr/learn/courses/30/lessons/150369)
  ### 구분
  코딩테스트 연습 > 2023 KAKAO BLIND RECRUITMENT
  ### 제출 일자
  2025년 10월 05일 17:31:01
"""

import json
import os
import re
import sys
from datetime import datetime

sys.path.insert(0, os.path.dirname(__file__))
from sm2 import create_initial_sm2


def parse_readme(readme_path: str) -> dict | None:
    """README.md를 파싱하여 문제 정보 딕셔너리 반환."""
    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    # 경로에서 소스 판별
    norm_path = readme_path.replace("\\", "/")
    if "/백준/" in norm_path:
        source = "백준"
    elif "/프로그래머스/" in norm_path:
        source = "프로그래머스"
    else:
        return None

    # 제목 줄: # [난이도] 제목 - 번호
    title_match = re.search(r"^# \[(.+?)\] (.+?) - (\d+)", content, re.MULTILINE)
    if not title_match:
        return None

    difficulty = title_match.group(1).strip()
    title = title_match.group(2).strip()
    number = int(title_match.group(3))

    # 문제 링크
    link_match = re.search(r"\[문제 링크\]\((.+?)\)", content)
    link = link_match.group(1) if link_match else ""

    # 카테고리
    category = []
    if source == "백준":
        cat_match = re.search(r"### 분류\s*\n(.+?)(?:\n#|\n\n|\Z)", content, re.DOTALL)
        if cat_match:
            raw = cat_match.group(1).strip().split("\n")[0]
            category = [c.strip() for c in raw.split(",") if c.strip()]
    else:
        cat_match = re.search(r"### 구분\s*\n(.+?)(?:\n#|\n\n|\Z)", content, re.DOTALL)
        if cat_match:
            raw = cat_match.group(1).strip().split("\n")[0]
            category = [c.strip() for c in raw.split(">") if c.strip()]

    # 제출 일자
    date_match = re.search(r"### 제출 일자\s*\n(.+)", content)
    first_solved = None
    if date_match:
        date_str = date_match.group(1).strip()
        # "2025년 9월 4일 18:15:49" or "2026년 02월 09일 16:36:00"
        m = re.match(r"(\d{4})년\s*(\d{1,2})월\s*(\d{1,2})일", date_str)
        if m:
            first_solved = f"{m.group(1)}-{int(m.group(2)):02d}-{int(m.group(3)):02d}"

    if not first_solved:
        return None

    # 언어 추정: README와 같은 디렉토리의 소스 파일 확장자
    language = "unknown"
    dir_path = os.path.dirname(readme_path)
    for fname in os.listdir(dir_path):
        if fname == "README.md":
            continue
        ext = os.path.splitext(fname)[1].lower()
        lang_map = {".py": "py", ".java": "java", ".cpp": "cpp", ".c": "c",
                    ".js": "js", ".sql": "sql", ".kt": "kt", ".swift": "swift"}
        if ext in lang_map:
            language = lang_map[ext]
            break

    # 문제 경로 (레포 루트 기준 상대 경로)
    repo_root = readme_path
    for _ in range(10):
        parent = os.path.dirname(repo_root)
        if os.path.basename(parent) in ("백준", "프로그래머스") or parent == repo_root:
            break
        repo_root = parent
    # 상대경로 계산
    base = readme_path
    parts = []
    while True:
        base, part = os.path.split(base)
        if part in ("백준", "프로그래머스"):
            parts.insert(0, part)
            break
        parts.insert(0, part)
        if not base:
            break
    rel_path = "/".join(parts[:-1])  # README.md 제외

    # 고유 ID
    source_prefix = "baekjoon" if source == "백준" else "programmers"
    problem_id = f"{source_prefix}_{number}"

    return {
        "id": problem_id,
        "source": source,
        "number": number,
        "title": title,
        "difficulty": difficulty,
        "category": category,
        "language": language,
        "link": link,
        "path": rel_path,
        "first_solved": first_solved,
    }


def is_easy_programmers(difficulty: str) -> bool:
    """프로그래머스 level 0, 1은 복습 불필요."""
    return difficulty in ("level 0", "level 1")


def register_problem(data: dict, problem_info: dict) -> bool:
    """review_data.json에 문제 등록. 이미 있으면 건너뜀. 등록 시 True 반환."""
    if is_easy_programmers(problem_info["difficulty"]):
        return False
    pid = problem_info["id"]
    if pid in data["problems"]:
        return False

    entry = dict(problem_info)
    entry["sm2"] = create_initial_sm2(problem_info["first_solved"])
    data["problems"][pid] = entry
    data["stats"]["total_problems"] = len(data["problems"])
    return True


def load_review_data(path: str) -> dict:
    """review_data.json 로드."""
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)


def save_review_data(path: str, data: dict):
    """review_data.json 저장."""
    with open(path, "w", encoding="utf-8") as f:
        json.dump(data, f, ensure_ascii=False, indent=2)
        f.write("\n")


if __name__ == "__main__":
    # CLI: python register_problem.py <readme_path> [review_data_path]
    if len(sys.argv) < 2:
        print("Usage: python register_problem.py <readme_path> [review_data.json]")
        sys.exit(1)

    readme_path = sys.argv[1]
    data_path = sys.argv[2] if len(sys.argv) > 2 else "review_data.json"

    info = parse_readme(readme_path)
    if not info:
        print(f"Failed to parse: {readme_path}")
        sys.exit(1)

    data = load_review_data(data_path)
    if register_problem(data, info):
        save_review_data(data_path, data)
        print(f"Registered: {info['id']} - {info['title']}")
    else:
        print(f"Already exists: {info['id']}")
