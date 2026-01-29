import sys

input = sys.stdin.readline

cycle = {
    0: [10],
    1: [1],
    2: [2, 4, 8, 6],
    3: [3, 9, 7, 1],
    4: [4, 6],
    5: [5],
    6: [6],
    7: [7, 9, 3, 1],
    8: [8, 4, 2, 6],
    9: [9, 1],
}

T = int(input())
for _ in range(T):
    a, b = map(int, input().split())
    a %= 10
    c = cycle[a]
    print(c[(b - 1) % len(c)])
