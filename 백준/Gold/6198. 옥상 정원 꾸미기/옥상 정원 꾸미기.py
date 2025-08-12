import sys
input = sys.stdin.readline

n = int(input())
stack = []
ans = 0

for _ in range(n):
    h = int(input())
    while stack and stack[-1] <= h:
        stack.pop()
    ans += len(stack)
    stack.append(h)


print(ans)