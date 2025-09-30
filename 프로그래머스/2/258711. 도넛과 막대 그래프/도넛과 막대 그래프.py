def solution(edges):
    # 정점 최대 번호
    mx = 0
    for a, b in edges:
        if a > mx: mx = a
        if b > mx: mx = b

    indeg = [0] * (mx + 1)
    outdeg = [0] * (mx + 1)

    for a, b in edges:
        outdeg[a] += 1
        indeg[b] += 1

    # 생성 정점: 진입 0, 진출 >= 2
    gen = 0
    for i in range(1, mx + 1):
        if indeg[i] == 0 and outdeg[i] >= 2:
            gen = i
            break

    # 막대: outdeg == 0 (단말) — 생성 정점 제외
    sticks = 0
    # 8자: indeg >= 2 and outdeg >= 2 — 생성 정점 제외
    eights = 0
    for i in range(1, mx + 1):
        if i == gen:
            continue
        if outdeg[i] == 0 and indeg[i] > 0:
            sticks += 1
        elif indeg[i] >= 2 and outdeg[i] >= 2:
            eights += 1

    # 도넛: 생성 정점에서 나간 그래프 수 - 막대 - 8자
    donuts = outdeg[gen] - sticks - eights
    return [gen, donuts, sticks, eights]