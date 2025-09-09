import sys
from itertools import combinations

input = sys.stdin.readline

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

homes, chickens = [], []
for i in range(n):
    for j, v in enumerate(board[i]):
        if v == 1:
            homes.append((i, j))
        elif v == 2:
            chickens.append((i, j))

H, C = len(homes), len(chickens)

# 집 h에서 각 치킨집 c까지의 맨해튼 거리를 미리 계산 (H x C)
dists = [[abs(hx - cx) + abs(hy - cy) for (cx, cy) in chickens]
         for (hx, hy) in homes]

best = 10**9

# C개 치킨집 중 m개 선택 조합을 순회
for comb in combinations(range(C), m):
    total = 0
    # 각 집마다 선택된 치킨집들 중 최소 거리만 더함 (가지치기 포함)
    for h in range(H):
        row = dists[h]
        mh = 1_000_000
        for j in comb:
            dj = row[j]
            if dj < mh:
                mh = dj
                if mh == 0:  # 더 줄 수 없음
                    break
        total += mh
        if total >= best:  # 현재 최적 해보다 크면 더 볼 필요 없음
            break
    if total < best:
        best = total

print(best)