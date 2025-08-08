import sys
input=sys.stdin.readline

n,m=map(int,input().split())
num=list(map(int,input().split()))
num.sort()
res=[]
def backtrack(depth):
    if depth==m:
        print(' '.join(map(str, res)))
        return
    for i in range(n):
        res.append(num[i])
        backtrack(depth+1)
        res.pop()
backtrack(0)