import sys
from collections import deque
input=sys.stdin.readline
graph=[]
n=int(input())
visited=[ [0]*(n) for i in range (n)]
for i in range(n):
    graph.append(list(map(int,input().split())))



def bfs(start):
    q=deque([start])
    answer=[ 0 for _ in range(n)]
    while(q):
        now=q.popleft()
        for i in range (n):
            if answer[i]==0 and graph[now][i]==1:
                q.append(i)
                answer[i]=1
                visited[start][i]=1
for i in range(0,n):
    bfs(i)
for i in visited:
    print(*i)
