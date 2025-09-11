import sys
input = sys.stdin.readline

M, N = map(int, input().split())
count = {}

for _ in range(M):
    arr = list(map(int, input().split()))
    # 1) 좌표 압축에 필요한 고유값 정렬
    vals = sorted(set(arr))
    # 2) 값 -> 순위 매핑
    rank = {v: i for i, v in enumerate(vals)}
    # 3) 원래 배열을 순위 배열로 변환 (정규화 키)
    norm = tuple(rank[x] for x in arr)
    # 4) 같은 정규화 결과의 출현 횟수 카운팅
    count[norm] = count.get(norm, 0) + 1

# 5) 같은 정규화 결과를 가진 우주들끼리 쌍의 수 합산
ans = 0
for c in count.values():
    ans += c * (c - 1) // 2
print(ans)