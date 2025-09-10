import sys
input = sys.stdin.readline

n = int(input())
minus=[]
plus=[]
zero=0
for i in range(n):
    num = int(input())
    if num<0:
        minus.append(num)
    
    elif num>0:
        plus.append(num)
    else:
        zero+=1

res=0
plus.sort(reverse=True)
minus.sort()

if len(minus)%2==0:
    for i in range(0,len(minus),2):
        res+=minus[i]*minus[i+1]
else:
    for i in range(0,len(minus)-1,2):
        res+=minus[i]*minus[i+1]
    if zero==0:
        res+=minus[-1]

if len(plus)%2==0:
    for i in range(0,len(plus),2):
        if plus[i]*plus[i+1]>plus[i]+plus[i+1]:
            res+=plus[i]*plus[i+1]
        else:
            res+=plus[i]+plus[i+1]

else:
    for i in range(0,len(plus)-1,2):
        if plus[i]*plus[i+1]>plus[i]+plus[i+1]:
            res+=plus[i]*plus[i+1]
        else:
            res+=plus[i]+plus[i+1]        
        
    res+=plus[-1]

print(res)