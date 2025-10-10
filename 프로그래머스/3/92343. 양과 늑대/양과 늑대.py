def solution(info, edges):
    from collections import defaultdict

    graph = defaultdict(list)
    for p, c in edges:
        graph[p].append(c)

    max_sheep = 0

    def dfs(curr, sheep, wolf, next_nodes):
        nonlocal max_sheep
        if info[curr] == 0:
            sheep += 1
        else:
            wolf += 1

        if wolf >= sheep:
            return  # 늑대 수가 같거나 많아지면 실패

        max_sheep = max(max_sheep, sheep)

        # 현재 노드에서 갈 수 있는 새로운 노드들 추가
        new_next = next_nodes + graph[curr]
        new_next.remove(curr)  # 이미 방문한 노드 제거

        for nxt in new_next:
            dfs(nxt, sheep, wolf, new_next.copy())

    dfs(0, 0, 0, [0])
    return max_sheep