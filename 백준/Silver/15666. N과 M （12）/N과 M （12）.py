import sys
input = sys.stdin.readline

n, m = map(int, input().split())
num = list(map(int, input().split()))
num=list(set(num))
num.sort()
n=len(num)
res = []

def backtrack(depth,start):
    if depth == m:
        print(' '.join(map(str, res)))
        return
    prev = None  # 이번 깊이에서 이미 선택한 값 체크
    for i in range(start,n):
        if prev == num[i]:
            continue
        prev = num[i]
        res.append(num[i])
        backtrack(depth + 1,i)
        res.pop()
backtrack(0,0)