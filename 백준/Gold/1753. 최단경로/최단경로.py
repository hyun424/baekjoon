import sys

input = sys.stdin.readline

# 입력: V(정점 수), E(간선 수)
V, E = map(int, input().split())
K = int(input())  # 시작 정점

graph = [[] for _ in range(V + 1)]
for _ in range(E):
    u, v, w = map(int, input().split())
    graph[u].append((v, w))

INF = 10**15  # 정수 무한대
dist = [INF] * (V + 1)
visited = [False] * (V + 1)

dist[K] = 0

# 다익스트라 (배열 기반, O(V^2))
for _ in range(V):
    # 방문하지 않은 정점 중 최단거리 정점 선택
    u = -1
    min_d = INF
    for i in range(1, V + 1):
        if not visited[i] and dist[i] < min_d:
            min_d = dist[i]
            u = i

    # 더 이상 갱신 가능한 정점이 없으면 종료
    if u == -1:
        break

    visited[u] = True

    # 선택한 정점으로부터 인접한 간선 완화
    du = dist[u]
    for v, w in graph[u]:
        nd = du + w
        if nd < dist[v]:
            dist[v] = nd

# 출력 형식: 1..V, 도달 불가면 INF
out = []
for i in range(1, V + 1):
    out.append("INF" if dist[i] == INF else str(dist[i]))
print("\n".join(out))
