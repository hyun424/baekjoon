import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def cal(i, time, graph, d):
    if d[i] != -1:
        return d[i]
    
    max_prev = 0
    for pre in graph[i]:
        max_prev = max(max_prev, cal(pre, time, graph, d))

    d[i] = max_prev + time[i]
    return d[i]

t = int(input())
for _ in range(t):
    n, k = map(int, input().split())
    time = [0] + list(map(int, input().split()))
    graph = [[] for _ in range(n + 1)]

    for _ in range(k):
        a, b = map(int, input().split())
        graph[b].append(a)

    w = int(input())
    d = [-1] * (n + 1)
    print(cal(w, time, graph, d))