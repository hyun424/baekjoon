import sys
input = sys.stdin.readline

while True:
    arr = list(map(int, input().split()))
    n = arr[0]
    if n == 0:
        break
    hist = arr[1:]
    stack = []
    max_area = 0
    for i, h in enumerate(hist + [0]):
        start = i
        while stack and stack[-1][1] > h:
            idx, height = stack.pop()
            max_area = max(max_area, height * (i - idx))
            start = idx
        if not stack or stack[-1][1] < h:
            stack.append((start, h))
    print(max_area)