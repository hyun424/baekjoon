import sys
from itertools import combinations
from collections import deque

input = sys.stdin.readline

board = [list(input().strip()) for _ in range(5)]  # 'S'/'Y'
ans = 0

# 0~24 인덱스를 (r,c)로 변환하는 헬퍼
def rc(idx):
    return divmod(idx, 5)  # (r, c)

# 뽑은 7칸이 서로 연결돼 있는지 검사
def connected(sel):
    sel_set = set(sel)
    q = deque([sel[0]])
    seen = {sel[0]}
    while q:
        cur = q.popleft()
        r, c = rc(cur)
        for dr, dc in ((1,0),(-1,0),(0,1),(0,-1)):
            nr, nc = r+dr, c+dc
            if 0 <= nr < 5 and 0 <= nc < 5:
                nxt = nr*5 + nc
                if nxt in sel_set and nxt not in seen:
                    seen.add(nxt)
                    q.append(nxt)
    return len(seen) == 7

# 25칸에서 7칸 조합
cells = range(25)
for comb in combinations(cells, 7):
    # S가 4개 이상인지 빠르게 확인 (가지치기)
    s_cnt = 0
    for idx in comb:
        r, c = rc(idx)
        if board[r][c] == 'S':
            s_cnt += 1
            # 남은 칸 전부 Y여도 4 미만인 경우 조기 종료하지 않아도 되지만,
            # 충분히 빠르므로 단순 카운트만 사용
    if s_cnt < 4:
        continue

    # 연결성 검사
    if connected(comb):
        ans += 1

print(ans)