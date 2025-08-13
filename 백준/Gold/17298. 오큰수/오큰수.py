import sys
input = sys.stdin.readline

n = int(input())
a = list(map(int, input().split()))

ans = [-1] * n
stack = []  # store indices

for i, x in enumerate(a):
    while stack and a[stack[-1]] < x:
        ans[stack.pop()] = x
    stack.append(i)


print(' '.join(map(str, ans)))


