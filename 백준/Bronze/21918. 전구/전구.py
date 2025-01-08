n,m=map(int,input().split())
s=list(map(int,input().split()))
def flip(i):
    if s[i]==0:
        s[i]=1
    else:
        s[i]=0

for i in range(m):
    a,i,x=map(int,input().split())
    if a==1:
        s[i-1]=x
    elif a==2:
        for j in range(i-1,x):
            flip(j)
    elif a==3:
        for j in range(i-1,x):
            s[j]=0
    else:
        for j in range(i-1,x):
            s[j]=1
for _ in s:
    print(_, end=' ')
