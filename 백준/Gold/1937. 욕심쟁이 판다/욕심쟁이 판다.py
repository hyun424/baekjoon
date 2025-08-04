import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**6)
n=int(input())
graph=[]
for i in range(n):
    graph.append(list(map(int,input().split())))

dp=[[-1]* n for _ in range(n)]

dx=[1,-1,0,0]
dy=[0,0,-1,1]

def dfs(x,y):
    if dp[x][y]!=-1:
        return dp[x][y]
    
    dp[x][y]=1
    for i in range(4):
        nx=x+dx[i]
        ny=y+dy[i]
        if 0<=nx<n and 0<=ny<n:
            if graph[nx][ny]>graph[x][y]:
                dp[x][y]=max(dp[x][y],dfs(nx,ny)+1)
    return dp[x][y]

result=0
for i in range(n):
    for j in range(n):
        result =max(result,dfs(i,j))
print(result)