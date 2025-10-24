from itertools import combinations
from bisect import bisect_left

def solution(info, query):
    answer = []
    db = {}

    # info 처리
    for i in info:
        parts = i.split()
        conditions = parts[:-1]
        score = int(parts[-1])

        # 4개의 조건에서 '-'가 들어올 수 있는 모든 조합 생성
        for n in range(5):
            for c in combinations(range(4), n):
                temp = conditions.copy()
                for v in c:
                    temp[v] = '-'
                key = ''.join(temp)
                if key not in db:
                    db[key] = []
                db[key].append(score)

    # 각 조건별 점수 리스트를 정렬 (이진 탐색용)
    for key in db:
        db[key].sort()

    # query 처리
    for q in query:
        q = q.replace("and", "").replace("  ", " ")
        q = q.strip().split()
        key = ''.join(q[:-1])
        target = int(q[-1])

        if key in db:
            scores = db[key]
            idx = bisect_left(scores, target)
            answer.append(len(scores) - idx)
        else:
            answer.append(0)

    return answer