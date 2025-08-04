import sys
sys.setrecursionlimit(10**6)  

n, p, q = map(int, input().split())
dp = {0: 1}  # 기본값 A[0] = 1

def a(x):
    if x in dp:
        return dp[x]
    dp[x] = a(x // p) + a(x // q)
    return dp[x]

print(a(n))