from collections import Counter

n, c = map(int, input().split())
num = list(map(int, input().split()))

count = Counter(num)
# key: (빈도, 처음 등장 위치)
# Counter는 dict라서 순서를 보장 안 하니까 따로 index 기록
order = {}
for i, x in enumerate(num):
    if x not in order:
        order[x] = i

# 정렬: 빈도 내림차순, 처음 등장 순서 오름차순
num.sort(key=lambda x: (-count[x], order[x]))

print(*num)