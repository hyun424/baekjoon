import sys
from collections import deque
input = sys.stdin.readline

k = int(input())
w, h = map(int, input().split())
graph = [list(map(int, input().split())) for _ in range(h)]

monkey = [(1, 0), (0, 1), (-1, 0), (0, -1)]
knight = [(-2, -1), (-1, -2), (1, -2), (2, -1), (2, 1), (1, 2), (-1, 2), (-2, 1)]

# 방문 배열: (y, x, 말 이동 사용 횟수)
visited = [[[False] * (k + 1) for _ in range(w)] for __ in range(h)]

q = deque()
q.append((0, 0, 0, 0))  # x, y, 말 이동 사용 횟수, 시간(이동 수)
visited[0][0][0] = True

res = -1
while q:
    x, y, used, t = q.popleft()

    if x == w - 1 and y == h - 1:
        res = t
        print(res)
        sys.exit(0)

    # 원숭이(상하좌우) 이동
    for dx, dy in monkey:
        nx, ny = x + dx, y + dy
        if 0 <= nx < w and 0 <= ny < h and graph[ny][nx] == 0 and not visited[ny][nx][used]:
            visited[ny][nx][used] = True
            q.append((nx, ny, used, t + 1))

    # 말(나이트) 이동
    if used < k:
        for dx, dy in knight:
            nx, ny = x + dx, y + dy
            if 0 <= nx < w and 0 <= ny < h and graph[ny][nx] == 0 and not visited[ny][nx][used + 1]:
                visited[ny][nx][used + 1] = True
                q.append((nx, ny, used + 1, t + 1))

print(res)