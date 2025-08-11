import sys
input = sys.stdin.readline

n = int(input())
stack = []
output = []

for _ in range(n):
    cmd = input().split()

    if cmd[0] == "push":
        stack.append(cmd[1])
    elif cmd[0] == "pop":
        output.append(stack.pop() if stack else "-1")
    elif cmd[0] == "size":
        output.append(str(len(stack)))
    elif cmd[0] == "empty":
        output.append("0" if stack else "1")
    elif cmd[0] == "top":
        output.append(stack[-1] if stack else "-1")

# 한 번에 출력
sys.stdout.write("\n".join(output))