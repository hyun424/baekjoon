import sys
input=sys.stdin.readline


na,nb=map(int,input().split())
a=list(map(int,input().split()))
b=list(map(int,input().split()))

c=sorted(set(a)-set(b))
print(len(c))
print(*c)