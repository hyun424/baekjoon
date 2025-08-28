import sys
input=sys.stdin.readline
from collections import deque

T=int(input())
dx=[1,1,2,2,-1,-1,-2,-2]
dy=[2,-2,1,-1,2,-2,1,-1]
for _ in range(T):
    I=int(input())
    queue=deque()
    graph=[[0]* I for i in range(I)]
    s0,s1=map(int,input().split())
    e0,e1=map(int,input().split())
    if s0 == e0 and s1 == e1:
        print(0)
        continue
    graph[s0][s1]=1
    queue.append((s0,s1))
    found = False
    while queue and not found:
        x, y = queue.popleft()
        for i in range(8):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < I and 0 <= ny < I and graph[nx][ny] == 0:
                graph[nx][ny] = graph[x][y] + 1
                if nx == e0 and ny == e1:
                    found = True
                    break
                queue.append((nx, ny))

    print(graph[e0][e1]-1)