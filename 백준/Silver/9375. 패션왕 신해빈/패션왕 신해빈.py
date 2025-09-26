import sys
input=sys.stdin.readline

t=int(input())
for _ in range(t):
    cloth={}
    temp=[]
    n=int(input())
    for i in range(n):
        a,b=input().split()
        if b not in temp:
            temp.append(b)
        if cloth.get(b):
            cloth[b]+=1
        else:
            cloth[b]=1
    res=1
    for b in temp:
        res*=(cloth[b]+1)
    print(res-1)