import sys
input = sys.stdin.readline

N = int(input().strip())

v_col  = [0] * N         # 열 방문
v_d1   = [0] * (2*N - 1) # ↘ 대각선 (r + c)
v_d2   = [0] * (2*N - 1) # ↗ 대각선 (r - c + (N-1))

cnt = 0

def btk(r: int):
    nonlocal_cnt = 0  # 미세 최적화용 지역 변수 카운트 대신, 외부 cnt에 직접 쓰면 느릴 수 있음
    if r == N:
        return 1

    total = 0
    for c in range(N):
        d1 = r + c
        d2 = r - c + (N - 1)
        if v_col[c] == 0 and v_d1[d1] == 0 and v_d2[d2] == 0:
            v_col[c] = v_d1[d1] = v_d2[d2] = 1
            total += btk(r + 1)
            v_col[c] = v_d1[d1] = v_d2[d2] = 0
    return total

print(btk(0))