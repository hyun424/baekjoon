import sys
from collections import deque
input = sys.stdin.readline

n = int(input().strip())
graph = [list(input().strip()) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]


def bfs(sx, sy, visited, eq):
    q = deque([(sx, sy)])
    visited[sx][sy] = True
    color = graph[sx][sy]
    while q:
        x, y = q.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and eq(color, graph[nx][ny]):
                visited[nx][ny] = True
                q.append((nx, ny))


def count_regions(eq):
    visited = [[False] * n for _ in range(n)]
    cnt = 0
    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                cnt += 1
                bfs(i, j, visited, eq)
    return cnt


# 일반 시야: 같은 색만 연결
EqNormal = lambda a, b: a == b
# 적록색약 시야: R과 G를 같은 색으로 취급
EqColorBlind = lambda a, b: (a == b) or (a in ('R', 'G') and b in ('R', 'G'))

print(count_regions(EqNormal), count_regions(EqColorBlind))