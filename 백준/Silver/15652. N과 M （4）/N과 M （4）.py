    
import sys
input = sys.stdin.readline

n, m = map(int, input().split())
seq = []

def dfs(depth: int, start: int) -> None:
    if depth == m:
        print(' '.join(map(str, seq)))
        return
    for i in range(start, n + 1):
        seq.append(i)
        dfs(depth + 1, i)  # allow repetition, keep non-decreasing by starting from i
        seq.pop()


dfs(0, 1)