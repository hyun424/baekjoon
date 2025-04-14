from collections import deque
import sys
input=sys.stdin.readline

n,m,v=map(int,input().split())  

graph=[[]for _ in range(n+1)]
for _ in range(m):
    a,b=map(int,input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(1,n+1):  
    graph[i].sort()  # 인접 리스트를 정렬하여 방문 순서 보장

visited=[False]*(n+1)

def dfs(v):
    # 방문 표시
    visited[v] = True
    print(v, end=' ')
    # 그래프를 순환하면서 인접 노드들을 방문
    for i in graph[v]:
        # 만약 방문하지 않은 노드가 있다면
        if not visited[i]:
            # 탐색 시작
            dfs(i)
dfs(v)
print()

visited=[False]*(n+1)
visited[v] = True  # 시작 노드 방문 처리
def bfs(v):
    # 큐 생성 및 큐에 시작 노드 삽입
    q = deque()
    q.append(v)
    # 아직 방문해야 하는 노드가 있다면
    while q:
        # 큐에서 노드를 꺼내서 x에 저장
        x = q.popleft()
        print(x, end=' ')
        # 그래프를 탐색하다가 방문하지 않은 노드가 있다면
        for i in graph[x]:
            if not visited[i]:
                # 큐에 방문하라고 삽입하고 방문 처리
                q.append(i)
                visited[i] = True


bfs(v)  