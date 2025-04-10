n,k=map(int,input().split())
coins=[]
for i in range(n):
    coins.append(int(input()))

coins=sorted(coins)
#불가능한 경우는 -1 출력
#최소한의 동전 이용하는게 목표
dp=[0]*(k+1)

for c in coins:
    for i in range(c,k+1,c):
        dp[i]=dp[i-c]+1
    #print(dp)

for c in coins:
    for i in range(c,k+1):
        if dp[i]!=0 and dp[i-c]!=0:
            dp[i]=min(dp[i],dp[i-c]+1)
        elif dp[i]==0 and dp[i-c]!=0:
            dp[i]=dp[i-c]+1
if dp[k]==0:
    print(-1)
else:
    print(dp[k])
