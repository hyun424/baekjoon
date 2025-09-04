import sys
import math

input = sys.stdin.readline

mn, mx = map(int, input().split())
L = mx - mn + 1
marked = [False] * L 

limit = math.isqrt(mx)
for i in range(2, limit + 1):
    sq = i * i
    start = ((mn + sq - 1) // sq) * sq 
    for j in range(start, mx + 1, sq):
        marked[j - mn] = True

print(L - sum(marked))