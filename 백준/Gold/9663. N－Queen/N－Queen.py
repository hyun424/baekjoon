import sys

n = int(sys.stdin.readline())
mask = (1 << n) - 1  # 하위 n비트만 사용

# row: 현재 행, cols: 사용된 열, diag1: 사용된 ↘ 대각선, diag2: 사용된 ↗ 대각선

def dfs(row: int, cols: int, diag1: int, diag2: int) -> int:
    if row == n:
        return 1

    count = 0
    # 가능한 자리: 아직 사용되지 않은 열/대각선
    available = mask & ~(cols | diag1 | diag2)

    while available:
        bit = available & -available  # 가장 오른쪽 1비트 선택
        available -= bit              # 해당 위치 사용 처리
        count += dfs(
            row + 1,
            cols | bit,
            ((diag1 | bit) << 1) & mask,
            (diag2 | bit) >> 1,
        )

    return count

print(dfs(0, 0, 0, 0))
