import sys
input = sys.stdin.readline

n = int(input())
stack = []
ops = []
next_num = 1  # 다음에 push할 수 있는 최소 수

for _ in range(n):
    target = int(input())

    # target까지 스택에 push
    while next_num <= target:
        stack.append(next_num)
        ops.append('+')
        next_num += 1

    # 스택의 top이 target이면 pop, 아니면 불가능
    if stack and stack[-1] == target:
        stack.pop()
        ops.append('-')
    else:
        print('NO')
        sys.exit(0)

print('\n'.join(ops))