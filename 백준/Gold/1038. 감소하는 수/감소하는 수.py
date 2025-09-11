import sys, math
from itertools import combinations
n=int(input())

def combnum(length: int) -> list:
    if length == 1:
        
        return list(range(1, 10))
    temp = []
    for c in combinations(range(10), length): 
        num = 0
        for d in reversed(c):  
            num = num * 10 + d
        temp.append(num)
    return sorted(temp)




dp = [0] * 11
dp[1] = 9
for i in range(2, 11):
    dp[i] = dp[i-1] + math.comb(10, i)
    
if n > 1022:
    print(-1)
elif n == 0:
    print(0)
else:
    
    for length in range(1, 11):
        if n <= dp[length]:
            break
    numlist = combnum(length)
    print(numlist[n - 1 - dp[length - 1]])
