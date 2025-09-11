import sys
input = sys.stdin.readline

M, N = map(int, input().split())    
A = list(map(int, input().split()))  


hi = max(A)
if hi == 0:
    print(0)
    exit()

lo, ans = 1, 0
while lo <= hi:
    mid = (lo + hi) // 2  
    cnt = 0
    for x in A:
        cnt += x // mid
        if cnt >= M:    
            break

    if cnt >= M:         
        ans = mid
        lo = mid + 1
    else:                
        hi = mid - 1

print(ans)