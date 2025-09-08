import sys
input = sys.stdin.readline

n = int(input().strip())
board1 = [list(map(int, input().split())) for _ in range(n)]

def rotate(b):
    """b를 시계 방향으로 90도 회전한 새 보드 반환"""
    nb = [[0]*n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            nb[i][j] = b[n-1-j][i]
    return nb

def tilt(b, dir_):
    """보드 b를 dir_번 회전 후 왼쪽으로 기울이는 연산을 수행한 보드 반환"""
    bb = b
    for _ in range(dir_):
        bb = rotate(bb)

    # 각 행을 왼쪽으로 기울이기
    for i in range(n):
        tilted = [0]*n
        idx = 0
        for j in range(n):
            if bb[i][j] == 0:
                continue
            if tilted[idx] == 0:
                tilted[idx] = bb[i][j]
            elif tilted[idx] == bb[i][j]:
                tilted[idx] *= 2
                idx += 1
            else:
                idx += 1
                tilted[idx] = bb[i][j]
        # 결과를 덮어쓰기
        for j in range(n):
            bb[i][j] = tilted[j]
    return bb

mx = 0
for tmp in range(1024):  # 4^5
    board2 = [row[:] for row in board1]
    brute = tmp
    for _ in range(5):
        dir_ = brute % 4  # 0,1,2,3
        brute //= 4
        board2 = tilt(board2, dir_)
    # 최대 블록 값 갱신
    for i in range(n):
        for j in range(n):
            if board2[i][j] > mx:
                mx = board2[i][j]

print(mx)