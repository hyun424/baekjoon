from collections import deque

n, k = map(int, input().split())
MAX = 100001
visited = [False] * (MAX + 1)

def bfs():
    queue = deque()
    queue.append((n, 0))
    visited[n] = True

    while queue:
        x, time = queue.popleft()

        if x == k:
            return time

        for nx in (x - 1, x + 1, x * 2):
            if 0 <= nx <= MAX and not visited[nx]:
                visited[nx] = True
                queue.append((nx, time + 1))

print(bfs())