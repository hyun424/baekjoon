import sys
from collections import deque
input = sys.stdin.readline

# 2573 빙산 — coding-test style (no main)

def count_groups(board, n, m):
    visited = [[False] * m for _ in range(n)]
    groups = 0
    dx = (-1, 1, 0, 0)
    dy = (0, 0, -1, 1)

    for i in range(n):
        for j in range(m):
            if board[i][j] > 0 and not visited[i][j]:
                groups += 1
                q = deque([(i, j)])
                visited[i][j] = True
                while q:
                    x, y = q.popleft()
                    for k in range(4):
                        nx, ny = x + dx[k], y + dy[k]
                        if 0 <= nx < n and 0 <= ny < m and board[nx][ny] > 0 and not visited[nx][ny]:
                            visited[nx][ny] = True
                            q.append((nx, ny))
    return groups


def melt(board, n, m):
    dx = (-1, 1, 0, 0)
    dy = (0, 0, -1, 1)
    melt_cnt = [[0] * m for _ in range(n)]

    for i in range(n):
        for j in range(m):
            if board[i][j] > 0:
                cnt = 0
                for k in range(4):
                    ni, nj = i + dx[k], j + dy[k]
                    if 0 <= ni < n and 0 <= nj < m and board[ni][nj] == 0:
                        cnt += 1
                melt_cnt[i][j] = cnt

    for i in range(n):
        for j in range(m):
            if board[i][j] > 0:
                board[i][j] = max(0, board[i][j] - melt_cnt[i][j])



n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

years = 0
while True:
    groups = count_groups(board, n, m)
    if groups >= 2:
        print(years)
        break
    if groups == 0:
        print(0)
        break
    melt(board, n, m)
    years += 1
