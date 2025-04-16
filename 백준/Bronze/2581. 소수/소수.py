n=int(input())
m= int(input())
def prime(num):
    div=[]
    for i in range(1,num+1):
        if(num%i==0):
            div.append(i)
    if len(div)==2:
        return 1
    else:
        return 0

sum=0      
numlist=[]
for i in range(n,m+1):
    if prime(i)==1:
        numlist.append(i)
        sum+=i
if sum==0:
    print(-1)
else:
    print(sum)
    print(numlist[0])
