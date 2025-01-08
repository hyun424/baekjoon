import sys
from collections import deque
#sys.setrecursionlimit(10**6) #재귀 깊이 때문에 recursionError 나서 추가
input=sys.stdin.readline
n,m=map(int,input().split())
graph=[[]for i in range(n+1)]

for i in range(m):
    a,b=map(int,input().split())
    graph[b].append(a)
visited=[0]*(n+1)

def bfs(v):
    
    cnt=0
    # 큐 생성 및 큐에 시작 노드 삽입
    q = deque()
    visited[v]=True
    q.append(v)
    # 아직 방문해야 하는 노드가 있다면
    while q:
        # 큐에서 노드를 꺼내서 x에 저장
        x = q.popleft()
        res[v]+=1
        # 그래프를 탐색하다가 방문하지 않은 노드가 있다면
        for i in graph[x]:
            if not visited[i]:
                # 큐에 방문하라고 삽입하고 방문 처리
                q.append(i)
                visited[i] = True
    return cnt
res=[0]*(n+1)
for i in range(1,n+1):
    bfs(i)
    
    visited=[0]*(n+1)
max=max(res)
for index,_ in enumerate(res):
    if _ ==max:
        print(index,end=' ')