n=int(input())
dp=[0]*(n+1)
dp[0]=2
for i in range(1,n+1):
    dp[i]=dp[i-1]*2-1
res=dp[n]**2
print(res)