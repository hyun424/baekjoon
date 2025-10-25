import sys
import heapq
input = sys.stdin.readline

def dijkstra(start, graph, n):
    INF = int(1e9)
    dist = [INF] * (n + 1)
    dist[start] = 0
    heap = [(0, start)]
    
    while heap:
        cost, now = heapq.heappop(heap)
        if dist[now] < cost:
            continue
        for next_node, next_cost in graph[now]:
            new_cost = cost + next_cost
            if new_cost < dist[next_node]:
                dist[next_node] = new_cost
                heapq.heappush(heap, (new_cost, next_node))
    return dist

# 입력
n, m, x = map(int, input().split())
graph = [[] for _ in range(n + 1)]
reverse_graph = [[] for _ in range(n + 1)]

for _ in range(m):
    a, b, t = map(int, input().split())
    graph[a].append((b, t))         # 정방향
    reverse_graph[b].append((a, t)) # 역방향

# X -> 모든 노드
dist_from_X = dijkstra(x, graph, n)
# 모든 노드 -> X (역방향 그래프 사용)
dist_to_X = dijkstra(x, reverse_graph, n)

# 왕복 거리 최댓값 계산
max_time = 0
for i in range(1, n + 1):
    max_time = max(max_time, dist_to_X[i] + dist_from_X[i])

print(max_time)

