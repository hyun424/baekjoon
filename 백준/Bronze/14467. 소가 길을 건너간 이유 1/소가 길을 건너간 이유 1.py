n=int(input())
cows=[[] for i in range(11)]
for i in range(n):
    a,b=map(int,input().split())
    cows[a].append(b)
cnt=0
for cow in cows:
    if(len(cow)>1):
        for i in range(len(cow)-1):
            if cow[i]!=cow[i+1]:
                cnt+=1
print(cnt)