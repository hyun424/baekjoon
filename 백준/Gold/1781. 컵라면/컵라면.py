import sys
import heapq
input = sys.stdin.readline

N = int(input())
arr = []

for _ in range(N):
    d, r = map(int, input().split())
    arr.append((d, r))

arr.sort()  # 데드라인 기준 정렬
heap = []

for d, r in arr:
    heapq.heappush(heap, r)
    
    if len(heap) > d:
        heapq.heappop(heap)  # 가장 작은 보상 제거

print(sum(heap))