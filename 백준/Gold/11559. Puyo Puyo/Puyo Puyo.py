
from collections import deque
import sys
input = sys.stdin.readline

ROWS, COLS = 12, 6

board = [list(input().strip()) for _ in range(ROWS)]

dirs = [(1,0), (-1,0), (0,1), (0,-1)]

def bfs(sr, sc, visited):
    color = board[sr][sc]
    q = deque([(sr, sc)])
    visited[sr][sc] = True
    cells = [(sr, sc)]
    while q:
        r, c = q.popleft()
        for dr, dc in dirs:
            nr, nc = r + dr, c + dc
            if 0 <= nr < ROWS and 0 <= nc < COLS and not visited[nr][nc] and board[nr][nc] == color:
                visited[nr][nc] = True
                q.append((nr, nc))
                cells.append((nr, nc))
    return cells

def apply_gravity():
    for c in range(COLS):
        write = ROWS - 1
        for r in range(ROWS - 1, -1, -1):
            if board[r][c] != '.':
                board[write][c] = board[r][c]
                if write != r:
                    board[r][c] = '.'
                write -= 1
        for r in range(write, -1, -1):
            board[r][c] = '.'

def pop_groups():
    visited = [[False] * COLS for _ in range(ROWS)]
    to_pop = []
    for r in range(ROWS):
        for c in range(COLS):
            if board[r][c] != '.' and not visited[r][c]:
                cells = bfs(r, c, visited)
                if len(cells) >= 4:
                    to_pop.extend(cells)
    if not to_pop:
        return False
    for r, c in to_pop:
        board[r][c] = '.'
    return True

chains = 0
while True:
    popped = pop_groups()
    if not popped:
        break
    apply_gravity()
    chains += 1

print(chains)
