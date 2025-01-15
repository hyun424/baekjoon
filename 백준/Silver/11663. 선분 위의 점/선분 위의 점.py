import sys
input=sys.stdin.readline


n,m=map(int,input().split())
a=list(map(int,input().split()))


a.sort()
res=[]
for i in range(m):
    x,y=map(int,input().split())
    start=0
    end=n-1
    

    while start<=end:
        mid=(start+end)//2
        if a[mid]<x:
            start=mid+1
        else:
            end=mid-1
        
    res_x=end+1
    start=0
    end=n-1
    while start<=end:
        mid=(start+end)//2
        if a[mid]>y:
            end=mid-1
        else:
            start=mid+1
    res_y=end
    res.append(1+res_y-res_x)
for _ in res:
    print(_)