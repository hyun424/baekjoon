import sys
input = sys.stdin.readline

n = int(input())
# 전체 너비는 2*n - 1
w = 2 * n - 1
board = [[' '] * w for _ in range(n)]

def draw(x: int, y: int, size: int) -> None:
  """
  (x, y)를 꼭짓점(정중앙 열)으로 하는 높이 size의 정삼각형을 그린다.
  size는 3 * 2^k 형태로만 주어진다.
  """
  if size == 3:
    board[x][y] = '*'
    board[x + 1][y - 1] = '*'
    board[x + 1][y + 1] = '*'
    for d in range(-2, 3):
      board[x + 2][y + d] = '*'
    return

  half = size // 2
  draw(x, y, half)
  draw(x + half, y - half, half)
  draw(x + half, y + half, half)

draw(0, n - 1, n)

print('\n'.join(''.join(row) for row in board))
