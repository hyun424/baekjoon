import sys
input = sys.stdin.readline

n, s = map(int, input().split())
arr = list(map(int, input().split()))

INF = n + 1  

ans = INF

curr = 0
right = 0


for left in range(n):
    
    while right < n and curr < s:
        curr += arr[right]
        right += 1
        
    if curr >= s:
        ans = min(ans, right - left)
        
    curr -= arr[left]

print(0 if ans == INF else ans)