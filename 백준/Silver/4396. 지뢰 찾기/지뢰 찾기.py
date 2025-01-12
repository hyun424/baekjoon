n=int(input())
graph=[]
opened=[] 
dx=[-1,-1,-1,0,0,1,1,1]
dy=[-1,0,1,-1,1,-1,0,1]
flag=0
for i in range(n):
    graph.append(list(input()))
for i in range(n):
    opened.append(list(input()))

res=[['.']* n for i in range(n)]

for x in range(n):
    for y in range(n):
        cnt=0

        if(opened[x][y]=='x'):
            for i in range(8):
                nx=x+dx[i]
                ny=y+dy[i]
                if 0<=nx<n and 0<=ny<n and graph[nx][ny]=='*':
                    cnt+=1
            res[x][y]=cnt
        if (opened[x][y]=='x' and graph[x][y]=='*'):
            flag=1
if flag==1:
    for i in range(n):
        for j in range(n):
            if graph[i][j]=='*':
                res[i][j] = '*'

    
for row in res:
    for c in row:
        print(c, end='')
    print()
                