import sys
n,m=map(int,input().split())
sys.setrecursionlimit(10**6)
graph=[]
for i in range(m):
    graph.append(list(input()))
check=[[0]*n for _ in range(m)]

dx=[1,-1,0,0]##상하좌우
dy=[0,0,-1,1]

res=[[0] for _ in range(2)] 

temp=0

def dfs(x,y,color):
    global temp

    if x>=0 and x<m and y>=0 and y<n:
        if check[x][y]==1:
     
            return 
        else:
      
            if color==graph[x][y]:
                temp+=1
                check[x][y]=1
                for i in range(4):
                    dfs(x+dx[i],y+dy[i],color)
            else:
                return



for i in range(m):
    for j in range(n):
        temp=0
        dfs(i,j,'W')
        if temp!=0:
            res[0].append(temp**2)
        temp=0
        dfs(i,j,'B')

        if temp!=0:
            res[1].append(temp**2)

print(sum(res[0]),sum(res[1]))