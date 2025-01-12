import sys
graph=[]
num=[]
for i in range(5):
    graph.append(list(map(int,input().split())))
for i in range(5):
    a,b,c,d,e=map(int,input().split())
    num.append(a)
    num.append(b)
    num.append(c)
    num.append(d)
    num.append(e)
temp=0
def check():
    res=0
    cnt1,cnt2=0,0
    for i in range(5):
        if graph[i][i]=='x':
            cnt1+=1
        
        if graph[i][4-i]=='x':
            cnt2+=1

    for i in range(5):
        cnt3=0
        cnt4=0
        for j in range(5):
    
            if graph[i][j]=='x':
                cnt3+=1
            if cnt3==5:
                res+=1
        
            if graph[j][i]=='x':
                cnt4+=1
                if cnt4==5:
                    res+=1
               
    if cnt1==5:
        res+=1
        cnt1=0
    if cnt2==5:
        res+=1
        cnt2=0
    
    return res

for i in range(25):
    for x in range(5):
        for y in range(5):
            if num[i]==graph[x][y]:
                graph[x][y]='x'
    if 3<=check():
        print(i+1)
        sys.exit()
