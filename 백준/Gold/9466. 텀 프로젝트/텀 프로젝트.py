import sys
input = sys.stdin.readline


T = int(input())
out_lines = []
for _ in range(T):
    n = int(input())
    a = [0] + list(map(int, input().split()))
    state = [0]*(n+1)
    in_team = 0

    for i in range(1, n+1):
        if state[i]:
            continue
        stack = []
        cur = i
        while True:
            stack.append(cur)
            state[cur] = 1
            nxt = a[cur]
            if state[nxt] == 0:
                cur = nxt
                continue
            if state[nxt] == 1:
                cnt = 1
                v = a[nxt]
                while v != nxt:
                    cnt += 1
                    v = a[v]
                in_team += cnt
            for v in stack:
                state[v] = 2
            break

    out_lines.append(str(n - in_team))

sys.stdout.write("\n".join(out_lines))