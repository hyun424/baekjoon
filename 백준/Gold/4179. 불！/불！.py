from collections import deque
import sys
input = sys.stdin.readline

r, c = map(int, input().split())
graph = [list(input().strip()) for _ in range(r)]

# 불, 지훈이 좌표 저장용
fire_time = [[-1]*c for _ in range(r)]
jihoon_time = [[-1]*c for _ in range(r)]
queue_fire = deque()
queue_jihoon = deque()

# 초기 설정
for i in range(r):
    for j in range(c):
        if graph[i][j] == 'F':
            queue_fire.append((i, j))
            fire_time[i][j] = 0
        elif graph[i][j] == 'J':
            queue_jihoon.append((i, j))
            jihoon_time[i][j] = 0

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

# 1단계: 불 먼저 확산
while queue_fire:
    x, y = queue_fire.popleft()
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c:
            if graph[nx][ny] != '#' and fire_time[nx][ny] == -1:
                fire_time[nx][ny] = fire_time[x][y] + 1
                queue_fire.append((nx, ny))

# 2단계: 지훈이 이동
while queue_jihoon:
    x, y = queue_jihoon.popleft()
    # 탈출 조건: 가장자리 도달
    if x == 0 or x == r-1 or y == 0 or y == c-1:
        print(jihoon_time[x][y] + 1)
        exit()
    for d in range(4):
        nx = x + dx[d]
        ny = y + dy[d]
        if 0 <= nx < r and 0 <= ny < c:
            if graph[nx][ny] != '#' and jihoon_time[nx][ny] == -1:
                # 불이 안오거나, 불보다 먼저 도착 가능할 때만 이동
                if fire_time[nx][ny] == -1 or fire_time[nx][ny] > jihoon_time[x][y] + 1:
                    jihoon_time[nx][ny] = jihoon_time[x][y] + 1
                    queue_jihoon.append((nx, ny))

print("IMPOSSIBLE")