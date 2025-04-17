t=int(input())
n=10000
dp=[ ([0]*3) for i in range(n+1)]

for i in range(n+1):
    dp[i][0]=1
    dp[i][1]=i//2+dp[i][0]

    dp[0][2],dp[1][2],dp[2][2],dp[3][2]=0,1,2,3
for i in range(4,n+1):
    dp[i][2]=dp[i][1]+dp[i-3][2]
for i in range(t):
    temp=int(input())
    print(dp[temp][2])