def solution(commands):
    # 1) 기본 설정
    SIZE = 51  # 1..50 사용
    N = SIZE * SIZE

    parent = list(range(N))
    # 각 그룹의 값은 root 인덱스에만 저장, 빈 문자열("")은 값 없음
    value = [""] * N

    def idx(r, c):
        return (r - 1) * SIZE + (c - 1)

    def find(x):
        if parent[x] != x:
            parent[x] = find(parent[x])
        return parent[x]

    def union(a, b):
        ra, rb = find(a), find(b)
        if ra == rb:
            return ra
        # 임의로 rb를 ra에 합침 (랭크 필요 없음: 최대 2500이므로 충분히 빠름)
        parent[rb] = ra
        # MERGE 값 규칙:
        # - 두 그룹 모두 값이 있으면 (r1,c1) 쪽(=ra)의 값을 유지
        # - 한쪽만 값이 있으면 그 값을 선택
        if value[ra] == "" and value[rb] != "":
            value[ra] = value[rb]
        # 더 이상 root가 아닌 rb의 값은 비움
        value[rb] = ""
        return ra

    def update_cell(r, c, v):
        root = find(idx(r, c))
        value[root] = v

    def update_all(v1, v2):
        # 그룹 단위로 한 번만 바꾸기 위해 root 집합을 사용
        seen = set()
        for i in range(N):
            r = find(i)
            if r not in seen:
                seen.add(r)
                if value[r] == v1:
                    value[r] = v2

    def merge_cells(r1, c1, r2, c2):
        a, b = idx(r1, c1), idx(r2, c2)
        union(a, b)

    def unmerge_cell(r, c):
        me = idx(r, c)
        root = find(me)
        keep = value[root]  # (r,c)만 보존할 값
        # 같은 그룹의 모든 멤버 수집
        members = []
        for i in range(N):
            if find(i) == root:
                members.append(i)
        # 그룹 해체: 각 멤버를 자기 자신이 루트가 되도록
        for m in members:
            parent[m] = m
            value[m] = ""   # 새 그룹(단일 칸)은 기본적으로 값 없음
        # (r,c)만 이전 값을 보존
        value[me] = keep

    def print_cell(r, c):
        root = find(idx(r, c))
        return value[root] if value[root] != "" else "EMPTY"

    # 2) 명령 처리
    answer = []
    for cmd in commands:
        parts = cmd.split()
        op = parts[0]

        if op == "UPDATE":
            if len(parts) == 4:
                # UPDATE r c value
                r, c = int(parts[1]), int(parts[2])
                v = parts[3]
                update_cell(r, c, v)
            else:
                # UPDATE value1 value2
                v1, v2 = parts[1], parts[2]
                update_all(v1, v2)

        elif op == "MERGE":
            r1, c1, r2, c2 = map(int, parts[1:5])
            # 같은 그룹이면 무시
            if find(idx(r1, c1)) != find(idx(r2, c2)):
                merge_cells(r1, c1, r2, c2)

        elif op == "UNMERGE":
            r, c = int(parts[1]), int(parts[2])
            unmerge_cell(r, c)

        elif op == "PRINT":
            r, c = int(parts[1]), int(parts[2])
            answer.append(print_cell(r, c))

    return answer