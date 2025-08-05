import sys
from collections import deque

# 첫 줄에서 상자의 가로 크기 M과 세로 크기 N을 입력받습니다.
M, N = map(int, sys.stdin.readline().split())

# 이후 N개의 줄에 걸쳐 토마토 상자의 상태 정보를 2차원 리스트로 입력받습니다.
box = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]

# 1)
# 전체 탐색 후 0이 없으면 -> 0
# 우선 익은 토마토(1) 큐에 넣어두기
# 2)
# 큐에서 하나씩 빼고 상하좌우에 0있으면 큐에넣고 1로 바꾸기
# 큐 끝나고 다시 전체탐색 후 0 남아있으면 -> -1

def solution (box, M, N) :
    queue = deque()
    answer = 0
    hasZero = False

    for i in range(N) :
        for j in range(M) :
            if box[i][j] == 1 :
                queue.append((i, j, 0))
            elif box[i][j] == 0:
                hasZero = True
    
    if hasZero == False :
         return 0
    
    while queue:
        x, y, day = queue.popleft()

        if day > answer :
            answer = day

        dx = [-1, 1, 0, 0]      # 상, 하
        dy = [0, 0, -1, 1]      # 좌, 우
        
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
        
            if 0 <= nx < N and 0 <= ny < M and box[nx][ny] == 0:
                queue.append((nx, ny, day+1))
                box[nx][ny] = 1
        
    for i in range(N) :
        for j in range(M) :
            if box[i][j] == 0 :
                return -1

    return answer


print(solution(box, M, N))