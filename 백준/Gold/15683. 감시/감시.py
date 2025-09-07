import sys
input = sys.stdin.readline

DX = [-1, 0, 1, 0]
DY = [0, 1, 0, -1]

CCTV_DIRS = {
    1: [[0], [1], [2], [3]],
    2: [[0, 2], [1, 3]],
    3: [[0, 1], [1, 2], [2, 3], [3, 0]],
    4: [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],
    5: [[0, 1, 2, 3]],
}

n, m = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(n)]

cctvs = []
for i in range(n):
    for j in range(m):
        if 1 <= board[i][j] <= 5:
            cctvs.append((i, j, board[i][j]))


def fill(x: int, y: int, d: int, grid) -> list:
    changed = []
    nx, ny = x + DX[d], y + DY[d]
    while 0 <= nx < n and 0 <= ny < m:
        if grid[nx][ny] == 6:
            break
        if grid[nx][ny] == 0:  
            grid[nx][ny] = 7
            changed.append((nx, ny))

        nx += DX[d]
        ny += DY[d]
    return changed


def watch(x: int, y: int, dirs: list, grid) -> list:
    changed_total = []
    for d in dirs:
        changed_total.extend(fill(x, y, d, grid))
    return changed_total


best = n * m  


def dfs(idx: int):
    global best
    if idx == len(cctvs):
        
        blind = 0
        for r in range(n):
            for c in range(m):
                if board[r][c] == 0:
                    blind += 1
        if blind < best:
            best = blind
        return

    x, y, t = cctvs[idx]
    for dirs in CCTV_DIRS[t]:
        changed = watch(x, y, dirs, board)
        dfs(idx + 1)
        
        for rx, ry in changed:
            board[rx][ry] = 0


dfs(0)
print(best)
