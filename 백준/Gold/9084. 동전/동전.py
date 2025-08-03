import sys
input = sys.stdin.readline

t = int(input())
for _ in range(t):
    n = int(input())
    coins = list(map(int, input().split()))
    m = int(input())
    
    dp = [0] * (m + 1)
    dp[0] = 1  # 0원을 만드는 방법은 1가지 (아무것도 선택하지 않기)

    for coin in coins:
        for i in range(coin, m + 1):
            dp[i] += dp[i - coin]
    
    print(dp[m])