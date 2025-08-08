import sys
input = sys.stdin.readline

n, m = map(int, input().split())
num = list(map(int, input().split()))
num.sort()

res = []

def backtrack(depth):
    if depth == m:
        print(' '.join(map(str, res)))
        return
    prev = None  # 이번 깊이에서 이미 선택한 값 체크
    for i in range(n):
        if prev == num[i]:
            continue
        prev = num[i]
        res.append(num[i])
        backtrack(depth + 1)
        res.pop()
backtrack(0)