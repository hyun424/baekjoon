a,b=map(int,input().split())
res=1
while(b>a):
    if b%10==1:
        b=b//10
    else:

        b=b/2
    res+=1
if (a!=b):
    res=-1
print(res)