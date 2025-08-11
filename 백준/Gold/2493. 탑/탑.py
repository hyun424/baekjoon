import sys
input = sys.stdin.readline

n = int(input())
heights = list(map(int, input().split()))

stack = []              # (idx, height) — 높이 내림차순 유지
ans = [0] * n

for i, h in enumerate(heights, start=1):  # 1-based
    while stack and stack[-1][1] <= h:
        stack.pop()
    ans[i - 1] = stack[-1][0] if stack else 0
    stack.append((i, h))

print(*ans)