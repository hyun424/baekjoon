import sys
import heapq
input = sys.stdin.readline

N = int(input())

left = []   # 최대힙 (작은 값들)
right = []  # 최소힙 (큰 값들)

for _ in range(N):
    x = int(input())
    
    # 1) 먼저 left(max-heap)에 넣는다.
    heapq.heappush(left, -x)
    
    # 2) left의 최대값 > right의 최소값 => 교정
    if right and -left[0] > right[0]:
        a = -heapq.heappop(left)
        b = heapq.heappop(right)
        heapq.heappush(left, -b)
        heapq.heappush(right, a)
    
    # 3) 균형 맞추기: left는 항상 right보다 1개 많거나 같아야 함
    if len(left) > len(right) + 1:
        heapq.heappush(right, -heapq.heappop(left))
    
    print(-left[0])
