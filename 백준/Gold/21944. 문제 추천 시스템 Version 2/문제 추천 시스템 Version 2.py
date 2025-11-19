import sys
import bisect

input = sys.stdin.readline

N = int(input())
group = {i: [] for i in range(1, 101)}      # 그룹 G: (L, P)
problems = []                                # 전체: (L, P)
info = {}                                     # P -> (L, G)

# 초기 입력
for _ in range(N):
    P, L, G = map(int, input().split())
    bisect.insort(group[G], (L, P))
    bisect.insort(problems, (L, P))
    info[P] = (L, G)

M = int(input())

for _ in range(M):
    cmd = input().split()

    if cmd[0] == "recommend":
        G = int(cmd[1])
        x = int(cmd[2])

        if x == 1:
            print(group[G][-1][1])
        else:
            print(group[G][0][1])

    elif cmd[0] == "recommend2":
        x = int(cmd[1])
        if x == 1:
            print(problems[-1][1])
        else:
            print(problems[0][1])

    elif cmd[0] == "recommend3":
        x = int(cmd[1])
        L = int(cmd[2])

        if x == 1:
            # L 이상 중 가장 쉬운 문제
            idx = bisect.bisect_left(problems, (L, -1))
            if idx == len(problems):
                print(-1)
            else:
                print(problems[idx][1])

        else:
            # L 미만 중 가장 어려운 문제
            idx = bisect.bisect_left(problems, (L, -1))
            if idx == 0:
                print(-1)
            else:
                print(problems[idx - 1][1])

    elif cmd[0] == "add":
        P = int(cmd[1])
        L = int(cmd[2])
        G = int(cmd[3])

        bisect.insort(group[G], (L, P))
        bisect.insort(problems, (L, P))
        info[P] = (L, G)

    else:  # solved
        P = int(cmd[1])
        L, G = info[P]

        # group[G] 에서 제거
        idx = bisect.bisect_left(group[G], (L, P))
        group[G].pop(idx)

        # 전체 problems 에서 제거
        idx = bisect.bisect_left(problems, (L, P))
        problems.pop(idx)

        del info[P]
