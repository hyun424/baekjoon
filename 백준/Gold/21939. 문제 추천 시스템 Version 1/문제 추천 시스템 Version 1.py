import sys
import heapq

input = sys.stdin.readline

N = int(input())
min_heap = []                 # (L, P)
max_heap = []                 # (-L, -P)
alive = {}                    # P -> L  (현재 시스템에 존재하면 난이도 기록)

for _ in range(N):
    P, L = map(int, input().split())
    alive[P] = L
    heapq.heappush(min_heap, (L, P))
    heapq.heappush(max_heap, (-L, -P))

M = int(input())
out = []

for _ in range(M):
    cmd = input().split()
    if cmd[0] == "add":
        P = int(cmd[1]); L = int(cmd[2])
        alive[P] = L
        heapq.heappush(min_heap, (L, P))
        heapq.heappush(max_heap, (-L, -P))

    elif cmd[0] == "solved":
        P = int(cmd[1])
        # lazy deletion: alive에서만 제거, 힙에서의 쓰레기는 나중에 추천 시 걸러냄
        if P in alive:
            del alive[P]

    else:  # "recommend"
        x = int(cmd[1])
        if x == 1:
            # 난이도 가장 높은, 같다면 번호 가장 큰
            while max_heap:
                negL, negP = max_heap[0]
                L, P = -negL, -negP
                if P in alive and alive[P] == L:
                    out.append(str(P))
                    break
                heapq.heappop(max_heap)
        else:
            # 난이도 가장 낮은, 같다면 번호 가장 작은
            while min_heap:
                L, P = min_heap[0]
                if P in alive and alive[P] == L:
                    out.append(str(P))
                    break
                heapq.heappop(min_heap)

print("\n".join(out))