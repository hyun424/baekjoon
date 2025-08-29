import sys
from collections import deque
input = sys.stdin.readline

n, m = map(int, input().split())
graph = [list(map(int, input().rstrip())) for _ in range(n)]

# visited[x][y][z] : (x,y)에 z상태(0=벽 안부숨, 1=벽 부숨)로 방문 여부
visited = [[[0]*2 for _ in range(m)] for _ in range(n)]

dx = [-1,1,0,0]
dy = [0,0,-1,1]

queue = deque()
queue.append((0,0,0))  # x, y, crash
visited[0][0][0] = 1   # 시작 지점 거리 = 1

while queue:
    x, y, crash = queue.popleft()

    if x == n-1 and y == m-1:
        print(visited[x][y][crash])
        exit()

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx < n and 0 <= ny < m:
            # 길이고 아직 방문 안했으면
            if graph[nx][ny] == 0 and visited[nx][ny][crash] == 0:
                visited[nx][ny][crash] = visited[x][y][crash] + 1
                queue.append((nx, ny, crash))

            # 벽인데, 아직 안부쉈으면
            elif graph[nx][ny] == 1 and crash == 0 and visited[nx][ny][1] == 0:
                visited[nx][ny][1] = visited[x][y][crash] + 1
                queue.append((nx, ny, 1))

print(-1)