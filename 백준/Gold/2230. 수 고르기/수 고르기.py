import sys
input = sys.stdin.readline

n, m = map(int, input().split())
A = [int(input()) for _ in range(n)]
A.sort()


res = 2_000_000_001
right = 0
for left in range(n):
    
    while right < n and A[right] - A[left] < m:
        right += 1
    if right == n:
        break
    
    diff = A[right] - A[left]
    if diff < res:
        res = diff

print(res)