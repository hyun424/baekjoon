import sys
input = sys.stdin.readline

T = int(input())
for _ in range(T):
    seq = input().rstrip('\n')
    left, right = [], []
    for ch in seq:
        if ch == '<':
            if left:
                right.append(left.pop())
        elif ch == '>':
            if right:
                left.append(right.pop())
        elif ch == '-':
            if left:
                left.pop()
        else:
            left.append(ch)
    print(''.join(left + right[::-1]))