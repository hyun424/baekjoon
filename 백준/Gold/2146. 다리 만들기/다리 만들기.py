import sys
from collections import deque
input = sys.stdin.readline

DIRS = [(1,0),(-1,0),(0,1),(0,-1)]

def label_islands_and_borders(board, n):
    labels = [[0]*n for _ in range(n)]
    island_id = 0
    borders = []  
    for i in range(n):
        for j in range(n):
            if board[i][j] == 1 and labels[i][j] == 0:
                island_id += 1
                q = deque([(i, j)])
                labels[i][j] = island_id
                while q:
                    x, y = q.popleft()
                    is_border = False
                    for dx, dy in DIRS:
                        nx, ny = x + dx, y + dy
                        if 0 <= nx < n and 0 <= ny < n:
                            if board[nx][ny] == 1 and labels[nx][ny] == 0:
                                labels[nx][ny] = island_id
                                q.append((nx, ny))
                            elif board[nx][ny] == 0:
                                is_border = True
                    if is_border:
                        borders.append((x, y, island_id))
    return labels, borders


def min_bridge_length(board, n):
    labels, borders = label_islands_and_borders(board, n)

    owner = [[0]*n for _ in range(n)]  
    dist  = [[0]*n for _ in range(n)]   
    q = deque()


    ans = 10**9
    for x, y, lab in borders:
        for dx, dy in DIRS:
            nx, ny = x + dx, y + dy
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] == 0:
                if owner[nx][ny] == 0:
                    owner[nx][ny] = lab
                    dist[nx][ny] = 0
                    q.append((nx, ny))
                elif owner[nx][ny] != lab:
                    if 1 < ans:
                        ans = 1

    while q:
        x, y = q.popleft()
        for dx, dy in DIRS:
            nx, ny = x + dx, y + dy
            if not (0 <= nx < n and 0 <= ny < n):
                continue
            if board[nx][ny] != 0:  
                continue
            if owner[nx][ny] == 0:
                owner[nx][ny] = owner[x][y]
                dist[nx][ny] = dist[x][y] + 1
                q.append((nx, ny))
            elif owner[nx][ny] != owner[x][y]:
                cand = dist[x][y] + dist[nx][ny] + 2
                if cand < ans:
                    ans = cand
    return ans

n = int(input().strip())
board = [list(map(int, input().split())) for _ in range(n)]
print(min_bridge_length(board, n))
