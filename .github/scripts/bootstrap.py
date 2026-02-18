"""
최근 1개월 이내 풀이 문제를 일괄 등록하는 부트스트랩 스크립트.

사용: python bootstrap.py [repo_root] [review_data.json]
"""

import os
import sys
from datetime import datetime, timedelta, timezone

sys.path.insert(0, os.path.dirname(__file__))
from register_problem import parse_readme, register_problem, is_easy_programmers, load_review_data, save_review_data


def find_all_readmes(repo_root: str) -> list[str]:
    """백준/ 및 프로그래머스/ 하위의 모든 README.md 경로 반환."""
    readmes = []
    for source_dir in ("백준", "프로그래머스"):
        base = os.path.join(repo_root, source_dir)
        if not os.path.isdir(base):
            continue
        for root, dirs, files in os.walk(base):
            if "README.md" in files:
                readmes.append(os.path.join(root, "README.md"))
    return readmes


def main():
    repo_root = sys.argv[1] if len(sys.argv) > 1 else "."
    data_path = sys.argv[2] if len(sys.argv) > 2 else os.path.join(repo_root, "review_data.json")

    cutoff = (datetime.now(timezone.utc) - timedelta(days=30)).date()
    print(f"Cutoff date: {cutoff.isoformat()} (최근 1개월)")

    data = load_review_data(data_path)
    readmes = find_all_readmes(repo_root)
    print(f"Found {len(readmes)} README files")

    registered = 0
    skipped_old = 0
    skipped_dup = 0
    skipped_easy = 0
    failed = 0

    for readme_path in sorted(readmes):
        info = parse_readme(readme_path)
        if not info:
            failed += 1
            continue

        if is_easy_programmers(info["difficulty"]):
            skipped_easy += 1
            continue

        solved_date = datetime.strptime(info["first_solved"], "%Y-%m-%d").date()
        if solved_date < cutoff:
            skipped_old += 1
            continue

        if register_problem(data, info):
            registered += 1
            print(f"  [+] {info['id']}: {info['title']} ({info['difficulty']}) - {info['first_solved']}")
        else:
            skipped_dup += 1

    save_review_data(data_path, data)

    print(f"\n=== Bootstrap Complete ===")
    print(f"  Registered: {registered}")
    print(f"  Skipped (easy): {skipped_easy}")
    print(f"  Skipped (old): {skipped_old}")
    print(f"  Skipped (dup): {skipped_dup}")
    print(f"  Failed to parse: {failed}")
    print(f"  Total in review_data: {data['stats']['total_problems']}")


if __name__ == "__main__":
    main()
