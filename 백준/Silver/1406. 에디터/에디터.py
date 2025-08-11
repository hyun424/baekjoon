import sys
input = sys.stdin.readline

# 왼쪽 스택: 커서의 왼쪽에 있는 문자들, 오른쪽 스택: 커서의 오른쪽에 있는 문자들
left = list(input().rstrip('\n'))
right = []

m = int(input())
for _ in range(m):
    cmd = input().split()
    op = cmd[0]

    if op == 'L':
        if left:                      # 왼쪽에 문자가 있으면 커서를 왼쪽으로 한 칸
            right.append(left.pop())  
    elif op == 'D':
        if right:                     # 오른쪽에 문자가 있으면 커서를 오른쪽으로 한 칸
            left.append(right.pop())
    elif op == 'B':
        if left:                      # 커서 왼쪽의 문자 삭제
            left.pop()
    else:  # 'P x'
        left.append(cmd[1])           # 커서 왼쪽에 문자 추가

# 최종 문자열 출력 (오른쪽 스택은 뒤집어서 이어붙임)
print(''.join(left + right[::-1]))