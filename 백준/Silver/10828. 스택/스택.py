import sys
input=sys.stdin.readline
n=int(input())
stack=[]
for _ in range(n):
    cmd=list(input().split())
    op=cmd[0]

    if op=="push":
        stack.append(cmd[1])
    elif op=="pop":
        if not stack:
            print(-1)
        else:
            print(stack.pop())
    elif op=="size":
        print(len(stack))
    elif op=="empty":
        if stack:
            print(0)
        else:
            print(1)
    elif op=="top":
        if stack:
            print(stack[-1])
        else:
            print(-1)
