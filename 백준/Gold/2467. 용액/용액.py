import sys
input = sys.stdin.readline

N = int(input().strip())
arr = list(map(int, input().split()))

st, en = 0, N - 1
best = 2_000_000_001  
ans_l, ans_r = arr[st], arr[en]

while st < en:
    s = arr[st] + arr[en]
    if abs(s) < best:
        best = abs(s)
        ans_l, ans_r = arr[st], arr[en]
        if best == 0:
            break  
    if s < 0:
        st += 1 
    else:
        en -= 1  
print(ans_l, ans_r)