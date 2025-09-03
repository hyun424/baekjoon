from collections import deque

n, k = map(int, input().split())
MAX = 100000

dist = [-1] * (MAX + 1)
q = deque([n])
dist[n] = 0

while q:
    x = q.popleft()
    if x == k:
        print(dist[x])
        break

    nx = x * 2
    if nx <= MAX and (dist[nx] == -1 or dist[nx] > dist[x]):
        dist[nx] = dist[x]
        q.appendleft(nx)

    for nx in (x - 1, x + 1):
        if 0 <= nx <= MAX and dist[nx] == -1:
            dist[nx] = dist[x] + 1
            q.append(nx)
