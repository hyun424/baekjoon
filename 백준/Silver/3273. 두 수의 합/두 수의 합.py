n = int(input())
num = list(map(int, input().split()))
x = int(input())

seen = set()
res = 0

for v in num:
    if x - v in seen:
        res += 1
    seen.add(v)

print(res)