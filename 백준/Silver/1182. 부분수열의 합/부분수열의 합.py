import sys
input = sys.stdin.readline
n, s = map(int, input().split())
num = list(map(int, input().split()))

def backtrack(start: int, total: int) -> int:
    count = 0
    for i in range(start, n):
        nt = total + num[i]
        if nt == s:
            count += 1
        count += backtrack(i + 1, nt)
    return count

res = backtrack(0, 0)
print(res)
