import sys
input = sys.stdin.readline

n = int(input())
L = list(map(int, input().split()))
J = list(map(int, input().split()))

dp = [0] * 100  # 체력 0~99까지

for i in range(n):
    # 뒤에서부터 순회해야 중복 사용을 막을 수 있음
    for j in range(99, L[i] - 1, -1):
        dp[j] = max(dp[j], dp[j - L[i]] + J[i])

print(max(dp))