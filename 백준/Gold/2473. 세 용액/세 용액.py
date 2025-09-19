import sys
input=sys.stdin.readline

N=int(input())
arr=list(map(int,input().split()))
arr.sort()
res=[0,0,0]
mx=3_000_000_001
for i in range(N-2):
    left,right=i+1,N-1
    while left<right:
        s=arr[i]+arr[left]+arr[right]
        if abs(s)<mx:
            mx=abs(s)
            res=[arr[i],arr[left],arr[right]]
        if mx==0:
            break

        elif s<0:
            left+=1

        else:
            right-=1
print(*res)


             