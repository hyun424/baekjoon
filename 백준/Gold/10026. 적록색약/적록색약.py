import sys
from collections import deque
input = sys.stdin.readline

n = int(input().strip())
grid = [list(input().strip()) for _ in range(n)]

def bfs(sx, sy, board, visited):
    q = deque([(sx, sy)])
    visited[sx][sy] = True
    color = board[sx][sy]
    while q:
        x, y = q.popleft()
        for dx, dy in ((1,0),(-1,0),(0,1),(0,-1)):
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < n and not visited[nx][ny] and board[nx][ny] == color:
                visited[nx][ny] = True
                q.append((nx, ny))

def count_regions(board):
    visited = [[False]*n for _ in range(n)]
    cnt = 0
    for i in range(n):
        for j in range(n):
            if not visited[i][j]:
                cnt += 1
                bfs(i, j, board, visited)
    return cnt

# 1) 일반 시야
normal = count_regions(grid)

# 2) 적록색약: G를 R로 바꾼 복사본에서 동일 로직
cb_grid = [row[:] for row in grid]
for i in range(n):
    for j in range(n):
        if cb_grid[i][j] == 'G':
            cb_grid[i][j] = 'R'
color_blind = count_regions(cb_grid)

print(normal, color_blind)