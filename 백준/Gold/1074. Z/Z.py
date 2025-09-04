import sys
N, r, c = map(int, sys.stdin.readline().split())

ans = 0
size = 1 << N  # 2^N
while size > 1:
    size //= 2           # 이번 단계의 사분면 한 변 길이
    area = size * size   # 한 사분면이 차지하는 칸 수

    # 사분면 판별
    row_big = 1 if r >= size else 0
    col_big = 1 if c >= size else 0
    q = (row_big << 1) | col_big  # 0,1,2,3

    ans += q * area

    if row_big: r -= size
    if col_big: c -= size

print(ans)