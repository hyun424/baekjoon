import sys
from collections import deque
input = sys.stdin.readline

M, N, H = map(int, input().split())
box = [[[0]*M for _ in range(N)] for _ in range(H)]

for h in range(H):
    for n in range(N):
        box[h][n] = list(map(int, input().split()))

q = deque()
for h in range(H):
    for n in range(N):
        for m in range(M):
            if box[h][n][m] == 1:
                q.append((h, n, m))

# 상하좌우전후 (z,y,x)
dz = [-1, 1, 0, 0, 0, 0]
dy = [0, 0, -1, 1, 0, 0]
dx = [0, 0, 0, 0, -1, 1]

while q:
    z, y, x = q.popleft()
    for d in range(6):
        nz, ny, nx = z + dz[d], y + dy[d], x + dx[d]
        if 0 <= nz < H and 0 <= ny < N and 0 <= nx < M:
            if box[nz][ny][nx] == 0:                # 아직 안 익음이면
                box[nz][ny][nx] = box[z][y][x] + 1  # 일수 기록(전염)
                q.append((nz, ny, nx))

max_day = 1
for h in range(H):
    for n in range(N):
        for m in range(M):
            if box[h][n][m] == 0:
                print(-1)
                sys.exit(0)
            if box[h][n][m] > max_day:
                max_day = box[h][n][m]

print(0 if max_day == 1 else max_day - 1)