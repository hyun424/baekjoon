import heapq
import sys
input = sys.stdin.readline

N = int(input())
hq = []

for _ in range(N):
    nums = list(map(int, input().split()))
    for x in nums:
        if len(hq) < N:
            heapq.heappush(hq, x)
        else:
            if hq[0] < x:
                heapq.heapreplace(hq, x)

print(hq[0])
