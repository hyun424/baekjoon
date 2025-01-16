import sys
input=sys.stdin.readline


n,m=map(int,input().split())
tree=list(map(int,input().split()))
tree.sort()
def cut(tree,h):
    res=0
    for t in tree:
        if t>h:
            res+=t-h
    return res

start=0
end=tree[-1]
while start<=end:
    mid=(start+end)//2
    if m<=cut(tree,mid):
        start=mid+1
        res=start
        
        
    else:
        end=mid-1
print(res-1)
#결과값이 m보다 작을때 , m보다 클 떄
