import sys
input=sys.stdin.readline

n,m=map(int,input().split())

num=list(map(int,input().split()))

num.sort()

visited=[False]*(n)
res=[]
def backtrack(depth,start):
    if depth==m:
        print(' '.join(map(str,res)))
        return
    for i in range(start,n):
        if  not visited[i]:
            visited[i]==True
            res.append(num[i])
            backtrack(depth+1,i+1)
            res.pop()
            visited[i]=False

backtrack(0,0)
