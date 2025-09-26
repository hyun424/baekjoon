import sys
from functools import lru_cache
input=sys.stdin.readline

res = 0

n,m,k=map(int,input().split())

data=[list(input().rstrip()) for _ in range(n)]


dx=(-1,1,0,0,1,1,-1,-1)
dy=(0,0,-1,1,-1,1,-1,1)

nbrs = [[[( (i + dx[d]) % n, (j + dy[d]) % m ) for d in range(8)] for j in range(m)] for i in range(n)]

@lru_cache(None)
def move(x: int, y: int, idx: int) -> int:
    if idx == L:
        return 1
    cnt = 0
    t = target[idx]
    for nx, ny in nbrs[x][y]:
        if data[nx][ny] == t:
            cnt += move(nx, ny, idx + 1)
    return cnt


starts_by_char = {}
for i in range(n):
    for j in range(m):
        c = data[i][j]
        starts_by_char.setdefault(c, []).append((i, j))

for _ in range(k):
    target = list(input().rstrip())
    L = len(target)
    move.cache_clear()
    res = 0
    starts = starts_by_char.get(target[0], [])
    for i, j in starts:
        res += move(i, j, 1)
    print(res)
