n=int(input())
graph=[[0]*n for i in range(n)]
target=int(input())
temp=n*n
res=[0,0]
#방향과 횟수 정하기
count=[n]
for i in range(n-1,0,(-1)):
    count.append(i)
    count.append(i)
#print(count)
t1,t2=-1,0
a=[[1,0],[0,1],[-1,0],[0,-1]]
for index,num in enumerate (count):
    #방향 정하기
    
    direction=a[index%4]
    x,y=direction[0],direction[1]
    for i in range(num):
        t1+=x
        t2+=y
        graph[t1][t2]=temp
        if temp==target:
            res[0],res[1]=t1+1,t2+1
        temp-=1

for row in graph:
    for num in row:
        print(num,end=' ')
    print()
print(res[0],res[1])