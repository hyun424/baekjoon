import sys
input = sys.stdin.readline


n = int(input())
p = list(map(int, input().split()))

p.sort()  

ans = 0
running = 0
for x in p:
    running += x     
    ans += running    

print(ans)
