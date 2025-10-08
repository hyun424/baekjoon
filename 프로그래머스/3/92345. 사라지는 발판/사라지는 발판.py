import sys
sys.setrecursionlimit(10**6)

dx = [1, -1, 0, 0]
dy = [0, 0, 1, -1]

def solution(board, aloc, bloc):
    n, m = len(board), len(board[0])

    def dfs(a, b, turn):
        ax, ay = a
        bx, by = b

        # 현재 플레이어 위치
        if turn:  # A의 차례
            x, y = ax, ay
        else:     # B의 차례
            x, y = bx, by

        if board[x][y] == 0:
            return False, 0  # 발판 없음 → 즉시 패배

        win, lose = [], []

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < m and board[nx][ny]:
                board[x][y] = 0
                if turn:
                    result, cnt = dfs((nx, ny), b, not turn)
                else:
                    result, cnt = dfs(a, (nx, ny), not turn)
                board[x][y] = 1

                # 현재 턴 플레이어가 이기는 경우
                if not result:
                    win.append(cnt + 1)
                else:
                    lose.append(cnt + 1)

        if win:
            return True, min(win)
        if lose:
            return False, max(lose)
        return False, 0  # 이동 불가 → 패배

    return dfs(aloc, bloc, True)[1]