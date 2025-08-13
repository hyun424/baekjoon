import sys
input = sys.stdin.readline

n = int(input())
stack = []  
ans = 0

for _ in range(n):
    h = int(input())
    cnt = 1

    while stack and stack[-1][0] < h:
        ans += stack[-1][1]
        stack.pop()

    if stack and stack[-1][0] == h:
        same_h_cnt = stack[-1][1]
        ans += same_h_cnt
        cnt += same_h_cnt
        stack.pop()
        if stack:
            ans += 1
    else:
        if stack:
            ans += 1

    stack.append((h, cnt))

print(ans)