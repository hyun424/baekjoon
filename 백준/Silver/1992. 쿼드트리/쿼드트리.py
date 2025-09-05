import sys
input = sys.stdin.readline

n = int(input().strip())
graph = [input().strip() for _ in range(n)]


def compress(x: int, y: int, size: int) -> str:
    first = graph[x][y]
    for i in range(x, x + size):
        row = graph[i]
        for j in range(y, y + size):
            if row[j] != first:
                half = size // 2
                return (
                    "("
                    + compress(x, y, half)
                    + compress(x, y + half, half)
                    + compress(x + half, y, half)
                    + compress(x + half, y + half, half)
                    + ")"
                )
    return first


print(compress(0, 0, n))