from collections import deque

N = int(input())

graph = [[] for _ in range(N + 1)]
while True:
    a, b = map(int, input().split())
    if a == -1 and b == -1:
        break
    graph[a].append(b)
    graph[b].append(a)

def bfs(start):
    dist = [-1] * (N + 1)
    q = deque([start])
    dist[start] = 0

    while q:
        cur = q.popleft()
        for nxt in graph[cur]:
            if dist[nxt] == -1:
                dist[nxt] = dist[cur] + 1
                q.append(nxt)

    # start 자신 제외하고 가장 멀리 있는 사람의 거리 = 점수
    return max(dist[1:])

scores = []
min_score = 1e9

for i in range(1, N + 1):
    s = bfs(i)
    scores.append(s)
    min_score = min(min_score, s)

# 회장 후보들
candidates = [i for i in range(1, N + 1) if scores[i - 1] == min_score]

print(min_score, len(candidates))
print(*candidates)
