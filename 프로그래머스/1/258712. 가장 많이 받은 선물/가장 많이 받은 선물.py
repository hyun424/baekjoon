def solution(friends, gifts):
    n = len(friends)
    idx = {name: i for i, name in enumerate(friends)}

    # g[i][j] = i가 j에게 준 선물 횟수
    g = [[0] * n for _ in range(n)]
    given = [0] * n
    received = [0] * n

    for record in gifts:
        a, b = record.split()
        i, j = idx[a], idx[b]
        g[i][j] += 1
        given[i] += 1
        received[j] += 1

    gift_index = [given[i] - received[i] for i in range(n)]

    # 다음 달에 각자 몇 개 받는지
    next_recv = [0] * n
    for i in range(n):
        for j in range(i + 1, n):
            if g[i][j] > g[j][i]:
                next_recv[i] += 1
            elif g[i][j] < g[j][i]:
                next_recv[j] += 1
            else:
                if gift_index[i] > gift_index[j]:
                    next_recv[i] += 1
                elif gift_index[i] < gift_index[j]:
                    next_recv[j] += 1
                # 같으면 아무도 못 받음

    return max(next_recv) if n > 0 else 0