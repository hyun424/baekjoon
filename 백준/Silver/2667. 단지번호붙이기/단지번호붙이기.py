import sys
input=sys.stdin.readline

n=int(input())
graph=[]
for i in range(n):
    graph.append(list(map(int, input().rstrip())))

dx=[1, -1, 0, 0]
dy=[0, 0, -1, 1]
res=[]
cnt=0
def dfs(x,y):
    global cnt
    if x<0 or x>=n or y<0 or y>=n:
        return
    if graph[x][y]==1:  
        graph[x][y]=0
        cnt+=1
        for i in range(4):
            nx=x+dx[i]
            ny=y+dy[i]
            dfs(nx,ny)

for i in range(n):
    for j in range(n):
        if graph[i][j]==1:
            
            dfs(i,j)
            res.append(cnt)
            cnt=0
res=sorted(res)
print(len(res))
for _ in res:
    print(_)    