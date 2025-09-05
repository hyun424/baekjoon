import sys
input = sys.stdin.readline

n = int(input().strip())
graph = [list(map(int, input().split())) for _ in range(n)]

# cnt[0] -> -1, cnt[1] -> 0, cnt[2] -> 1
cnt = [0, 0, 0]

def solve(x: int, y: int, size: int) -> None:
    first = graph[x][y]
    uniform = True

    # 현재 구역이 모두 같은 수인지 확인
    for i in range(x, x + size):
        row = graph[i]
        for j in range(y, y + size):
            if row[j] != first:
                uniform = False
                break
        if not uniform:
            break

    if uniform:
        if first == -1:
            cnt[0] += 1
        elif first == 0:
            cnt[1] += 1
        else:  # first == 1
            cnt[2] += 1
        return

    # 9분할
    third = size // 3
    solve(x, y, third)
    solve(x, y + third, third)
    solve(x, y + 2 * third, third)
    solve(x + third, y, third)
    solve(x + third, y + third, third)
    solve(x + third, y + 2 * third, third)
    solve(x + 2 * third, y, third)
    solve(x + 2 * third, y + third, third)
    solve(x + 2 * third, y + 2 * third, third)

solve(0, 0, n)

print(cnt[0])
print(cnt[1])
print(cnt[2])