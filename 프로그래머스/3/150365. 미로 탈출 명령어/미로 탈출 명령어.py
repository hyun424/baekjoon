def solution(n, m, x, y, r, c, k):
    # 방향: 사전순 'd' < 'l' < 'r' < 'u'
    dirs = [('d', 1, 0), ('l', 0, -1), ('r', 0, 1), ('u', -1, 0)]

    def feasible(cx, cy, steps):
        # 남은 steps로 (r,c)에 도달 가능한가?
        dist = abs(cx - r) + abs(cy - c)
        return dist <= steps and (steps - dist) % 2 == 0

    # 시작점에서 k번 안에 도달 가능 여부 먼저 확인
    if not feasible(x, y, k):
        return "impossible"

    ans = []
    cx, cy = x, y
    for i in range(k):
        left = k - i - 1  # 이번에 한 칸 간 뒤 남는 이동 수
        moved = False
        for ch, dx, dy in dirs:
            nx, ny = cx + dx, cy + dy
            if 1 <= nx <= n and 1 <= ny <= m and feasible(nx, ny, left):
                ans.append(ch)
                cx, cy = nx, ny
                moved = True
                break
        if not moved:  # 안전장치 (논리상 여기 오면 불가능)
            return "impossible"
    return "".join(ans)