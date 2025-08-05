import sys
from collections import deque
input = sys.stdin.readline

m, n = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(n)]

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

queue = deque()
res = [[-1]*m for _ in range(n)]

for i in range(n):
    for j in range(m):
        if graph[i][j] == 1:
            queue.append((i, j))
            res[i][j] = 0
        elif graph[i][j] == -1:
            res[i][j] = -1  # 벽(토마토 없음)

while queue:
    x, y = queue.popleft()
    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]
        if 0 <= nx < n and 0 <= ny < m:
            if graph[nx][ny] == 0 and res[nx][ny] == -1:
                res[nx][ny] = res[x][y] + 1
                queue.append((nx, ny))

ans = 0
for i in range(n):
    for j in range(m):
        if graph[i][j] == 0 and res[i][j] == -1:
            print(-1)
            exit()
        if res[i][j] > ans:
            ans = res[i][j]
print(ans)