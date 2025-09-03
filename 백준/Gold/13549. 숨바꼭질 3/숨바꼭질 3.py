from collections import deque

n, k = map(int, input().split())
MAX = 100000

q = deque()
q.append((n, 0))
visited = [False] * (MAX + 1)
visited[n] = True

while q:
    x, t = q.popleft()
    if x == k:
        print(t)
        break

    # 순간이동 (0초 소요 → appendleft)
    nx = x * 2
    if nx <= MAX and not visited[nx]:
        visited[nx] = True
        q.appendleft((nx, t))

    # 걷기 (1초 소요 → append)
    for nx in (x - 1, x + 1):
        if 0 <= nx <= MAX and not visited[nx]:
            visited[nx] = True
            q.append((nx, t + 1))