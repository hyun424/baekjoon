import sys
input=sys.stdin.readline


n=int(input())
numlist=list(map(int,input().split()))

stack=[]
ans=[-1]*n


for i,h in enumerate (numlist):

    while stack and stack[-1][1] < h:
        a,b=stack.pop()
        ans[a]=h

    stack.append((i,h))


print(* ans)