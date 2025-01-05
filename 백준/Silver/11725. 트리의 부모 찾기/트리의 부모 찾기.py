from collections import deque
N=int(input())
graph=[[]*(N+1) for _ in range(N+1)]

parents=[[]*(N+1) for _ in range(N+1)]
for _ in range(N-1):
    a, b= map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

def DFS():
    queue=[1]    
    while queue:
        n=queue.pop()
        for i in graph[n]:
            queue.append(i)
            parents[i].append(n)
            graph[i].remove(n)
    return parents

ans=DFS()[2:N+1]
for i in ans:
    print(i[0],end='\n')