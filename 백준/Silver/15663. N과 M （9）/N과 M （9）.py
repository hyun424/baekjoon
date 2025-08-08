import sys
input = sys.stdin.readline

n, m = map(int, input().split())
num = list(map(int, input().split()))
num.sort()

res = []
used = [False] * n  # 인덱스 사용 여부

def backtrack(depth: int) -> None:
    if depth == m:
        print(' '.join(map(str, res)))
        return

    last = None  # 이 깊이에서 이미 사용한 값(중복 방지)
    for i in range(n):
        if used[i]:
            continue
        if last == num[i]:  # 같은 깊이에서 같은 값으로 시작하는 분기는 스킵
            continue

        used[i] = True
        res.append(num[i])
        backtrack(depth + 1)
        res.pop()
        used[i] = False
        last = num[i]

backtrack(0)
