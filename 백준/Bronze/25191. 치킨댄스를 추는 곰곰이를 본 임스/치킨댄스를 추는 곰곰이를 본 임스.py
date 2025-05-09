n=int(input())
a,b=map(int,input().split())
res=a//2+b
if res>=n:
    print(n)
else:
    print(res)