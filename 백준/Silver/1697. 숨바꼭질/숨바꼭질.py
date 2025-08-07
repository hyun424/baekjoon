from collections import deque
n,k=map(int,input().split())

dx=[2,1,1]
dy=[0,1,-1]
queue=deque()
queue.append((n,0))
visited=[False]*100002
visited[n]=True
while queue:
    
    x,t=queue.popleft()

    if x==k:
        print(t)
        exit()
    for i in range(3):
        nx= (x * dx[i]) + dy[i]
        if 0<=nx<=100001 and not visited[nx]:
            visited[nx]=True
            queue.append((nx,t+1))