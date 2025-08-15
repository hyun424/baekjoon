import sys
from collections import deque

input = sys.stdin.readline

n, l = map(int, input().split())
nums = list(map(int, input().split()))

dq = deque()
res = []

for i in range(n):
    while dq and nums[dq[-1]] > nums[i]:
        dq.pop()

    dq.append(i)

    while dq and dq[0] < i - l + 1:
        dq.popleft()

    res.append(nums[dq[0]])

print(*res)