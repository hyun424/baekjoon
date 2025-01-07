n=int(input())
switch=[-1]+list(map(int,input().split()))
p=int(input())

def flip(i):
    if switch[i]==0:
        switch[i]=1
    else:
        switch[i]=0
    return

for person in range(p):
    a,b=map(int,input().split())
    if a==1:#male
        for k in range(b,n+1,b):
            flip(k)

    else:#female
        flip(b)
        for i in range(n//2):
            if b+i>n or b-i<1:
                break
            if switch[b+i]==switch[b-i]:
                flip(b+i)
                flip(b-i)
            else:
                break
for i in range(1, n+1):
    print(switch[i], end = " ")
    if i % 20 == 0 :
        print()