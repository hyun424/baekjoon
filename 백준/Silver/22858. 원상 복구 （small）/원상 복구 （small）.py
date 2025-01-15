INF = 999999999
import sys 
input=sys.stdin.readline
n,k=map(int,input().split())
s=list(map(int,input().split()))
d=list(map(int,input().split()))
p=[0]*(n+1)
for i in range(k):
    for index,_ in enumerate(d):

        p[_-1]=s[index]
    s=p.copy()

for i in range(n):
    print(p[i],end=' ')