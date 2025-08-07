n, m = map(int, input().split())
visited = [False] * (n + 1)
result = []

def backtrack(depth):
    if depth == m:
        print(' '.join(map(str, result)))
        return
    for i in range(1, n + 1):
        if not visited[i]:
            visited[i] = True
            result.append(i)
            backtrack(depth + 1)
            result.pop()
            visited[i] = False

backtrack(0)