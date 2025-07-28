n=int(input())
dp=[0]*(1000001)
dp[1]=0
for i in range(2,n+1):
    dp[i]=min(dp[i-1],
              dp[i//3] if i%3==0 else dp[i-1],
              dp[i//2] if i%2==0 else dp[i-1]) + 1
print(dp[n])
while (n!=1):
    if n%3 ==0 and dp[n] == dp[n//3] + 1:
        print(n, end=' ')
        n = n//3
    elif n%2 ==0 and dp[n] == dp[n//2] + 1:
        print(n, end=' ')
        n = n//2
    else:
        print(n, end=' ')
        n = n-1
print(1)