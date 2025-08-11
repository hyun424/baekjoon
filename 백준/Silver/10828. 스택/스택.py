import sys
input = sys.stdin.readline

n = int(input())
stack = []
out = []

for _ in range(n):
    cmd = input().split()
    op = cmd[0]

    if op == "push":
        stack.append(cmd[1])                 # int() 변환하지 않음
    elif op == "pop":
        out.append(stack.pop() if stack else "-1")
    elif op == "size":
        out.append(str(len(stack)))
    elif op == "empty":
        out.append("0" if stack else "1")
    elif op == "top":
        out.append(stack[-1] if stack else "-1")

sys.stdout.write("\n".join(out))