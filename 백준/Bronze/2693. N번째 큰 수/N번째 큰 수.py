t=int(input())
res=[]
for i in range(t):
    a=list(map(int,input().split()))    
    a=sorted(a)
    res.append(a[7])

for _ in res:
    print(_)