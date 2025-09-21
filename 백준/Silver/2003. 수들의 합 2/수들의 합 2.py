import sys
input= sys.stdin.readline


n,m=map(int,input().split())
arr=list(map(int,input().split()))

right=0
curr=0
res=0

for left in range(n):


    while right<n and curr+arr[right]<=m:
        curr+=arr[right]
        right+=1
    if curr==m:
        res+=1
    curr-=arr[left]

print(res)

