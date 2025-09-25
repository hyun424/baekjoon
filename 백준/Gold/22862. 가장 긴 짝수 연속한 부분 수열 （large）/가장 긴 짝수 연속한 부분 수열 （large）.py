import sys
input=sys.stdin.readline


n,k=map(int,input().split())
arr=list(map(int,input().split()))


right=0
odd=0
curr=0
mx=0
for left in range(n):
    if arr[left] %2==0:
        temp=True
    else:
        temp=False
    while right<n and odd<=k:
        if arr[right]%2==1:
            odd+=1
        else:
            curr+=1
        right+=1
    mx=max(mx,curr)
    if temp is False:
        odd-=1
    else:
        curr-=1
print(mx)