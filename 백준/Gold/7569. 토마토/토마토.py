import sys
from collections import deque
input=sys.stdin.readline

m,n,h=map(int,input().split())
box=[[[0]*(m) for _ in range(n)] for i in range(h)]
# box[h][n][m]
#전후좌우상하
dx=[0,0,-1,1,0,0]
dy=[-1,1,0,0,0,0]
dz=[0,0,0,0,-1,1]
for i in range(h):
    for j in range(n):
        box[i][j]=list(map(int,input().split()))


queue=deque()


for i in range(h):
    for j in range(n):
        for k in range(m):
            if box[i][j][k]==1:
                queue.append((i,j,k))
visited=[[[False]*m for _ in range(n)] for i in range(h)]

while queue:
    x,y,z= queue.popleft()
    visited[x][y][z]=True
    for i in range(6):
        nx=x+dx[i]
        ny=y+dy[i]
        nz=z+dz[i]
        if 0<=nx<h and 0<=ny<n and 0<=nz<m:
            if box[nx][ny][nz]==0 and not visited[nx][ny][nz]:
                visited[nx][ny][nz]=True
                box[nx][ny][nz]=box[x][y][z]+1
                queue.append((nx,ny,nz))
max=1
for i in range(h):
    for j in range(n):
        for k in range(m):
            if box[i][j][k]==0:
                print(-1)
                exit()
            else:
                if max<box[i][j][k]:
                    max=box[i][j][k]
if max==1:
    print(0)

else:
    print(max-1)