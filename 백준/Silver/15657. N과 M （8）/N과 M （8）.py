import sys
input=sys.stdin.readline

n,m=map(int,input().split())
num=list(map(int,input().split()))
num.sort()
res=[]
def backtrack(depth,start):
    if depth==m:
        print(' '.join(map(str,res)))
        return
    for i in range(start,n):
        res.append(num[i])
        backtrack(depth+1,i)
        res.pop()

backtrack(0,0)