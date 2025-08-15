import sys
from collections import deque
input=sys.stdin.readline

t=int(input())

for _ in range(t):
    p=input().rstrip()
    n=int(input())
    numlist=deque()
    for s in input().strip()[1:-1].split(','):
        if s.isdigit():
            numlist.append(int(s))

    rev = False
    err = False
    for f in p:
        if f == 'R':
            rev = not rev
        elif f == 'D':
            if numlist:
                if not rev:
                    numlist.popleft()
                else:
                    numlist.pop()
            else:
                print("error")
                err = True
                break

    if not err:
        if rev:
            print("[" + ",".join(map(str, reversed(numlist))) + "]")
        else:
            print("[" + ",".join(map(str, numlist)) + "]")
