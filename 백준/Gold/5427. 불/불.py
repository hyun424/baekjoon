import sys
from collections import deque

input = sys.stdin.readline

dx = [-1, 1, 0, 0]
dy = [0, 0, -1, 1]

T = int(input())
for _ in range(T):
    w, h = map(int, input().split())
    grid = []
    fires = []
    start = None

    for i in range(h):
        row = list(input().rstrip())
        for j, ch in enumerate(row):
            if ch == '*':
                fires.append((i, j))
            elif ch == '@':
                start = (i, j)
        grid.append(row)

    # 1) 불의 도달 시간을 BFS로 계산 (-1: 아직 도달 안함)
    fire_time = [[-1] * w for _ in range(h)]
    fq = deque()
    for fx, fy in fires:
        fire_time[fx][fy] = 0
        fq.append((fx, fy))

    while fq:
        x, y = fq.popleft()
        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            if 0 <= nx < h and 0 <= ny < w:
                if grid[nx][ny] != '#' and fire_time[nx][ny] == -1:
                    fire_time[nx][ny] = fire_time[x][y] + 1
                    fq.append((nx, ny))

    # 2) 상근이의 이동 BFS (불보다 먼저/동시에 도착하는 칸은 불가)
    visited = [[False] * w for _ in range(h)]
    sx, sy = start
    q = deque([(sx, sy, 0)])
    visited[sx][sy] = True

    escaped = False
    answer = None

    while q and not escaped:
        x, y, t = q.popleft()

        # 현재 칸이 가장자리라면, 다음 1스텝에 탈출 가능
        if x == 0 or x == h - 1 or y == 0 or y == w - 1:
            answer = t + 1
            escaped = True
            break

        for k in range(4):
            nx, ny = x + dx[k], y + dy[k]
            nt = t + 1
            if 0 <= nx < h and 0 <= ny < w:
                if not visited[nx][ny] and grid[nx][ny] != '#':
                    # 불이 아예 안 오거나(fire_time == -1) 우리보다 나중에 오면 이동 가능
                    if fire_time[nx][ny] == -1 or fire_time[nx][ny] > nt:
                        visited[nx][ny] = True
                        q.append((nx, ny, nt))

    if escaped:
        print(answer)
    else:
        print("IMPOSSIBLE")
    