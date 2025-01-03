n=int(input())
tips=[]
for i in range(n):
    tips.append(int(input()))
tips.sort(reverse= True)
res=0
for index,tip in enumerate (tips):
    tip-=index
    if (tip<=0):
        tip=0
    res+=tip
print(res)