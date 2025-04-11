import sys
input=sys.stdin.readline
sys.setrecursionlimit(10**6)

dx=[1,-1,0,0]
dy=[0,0,-1,1]
flag=0
def dfs(x,y):
    global flag
    if x<0 or y<0 or x>=n or y>=m:
        return False
    if graph[x][y]==1:
        flag=1
        graph[x][y]=0
        for i in range(4):
            dfs(x+dx[i],y+dy[i])
        return True
    return False


t=int(input())
for i in range(t):
    res=0
    m,n,k=map(int,input().split())
    graph=[[0 for i in range(m)] for i in range(n)]
    
    for i in range(k):
        a,b=map(int,input().split())
        graph[b][a]=1
    # for _ in graph:
    #     print(_)
    for y in range(m):
        for x in range(n):
            if graph[x][y]==1:
                dfs(x,y)
                if flag==1:
                    res+=1
                flag=0
    print(res)