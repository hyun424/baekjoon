import sys, math
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    x, y = map(int, input().split())
    d = y - x
    n = math.isqrt(d)           # n^2 <= d < (n+1)^2

    if d == n * n:
        print(2 * n - 1)
    elif d <= n * n + n:
        print(2 * n)
    else:
        print(2 * n + 1)