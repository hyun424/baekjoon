import sys
from array import array
input = sys.stdin.readline

n = int(input())
A, B, C, D = [], [], [], []
for _ in range(n):
    a, b, c, d = map(int, input().split())
    A.append(a)
    B.append(b)
    C.append(c)
    D.append(d)

n2 = n * n
AB = array('i')
CD = array('i')
append_ab = AB.append
append_cd = CD.append

for a in A:
    for b in B:
        append_ab(a + b)
for c in C:
    for d in D:
        append_cd(c + d)

AB = sorted(AB)
CD = sorted(CD)

i = 0
j = len(CD) - 1
res = 0
while i < len(AB) and j >= 0:
    s = AB[i] + CD[j]
    if s == 0:
        ai = AB[i]
        cntA = 0
        while i < len(AB) and AB[i] == ai:
            i += 1
            cntA += 1
        dj = CD[j]
        cntD = 0
        while j >= 0 and CD[j] == dj:
            j -= 1
            cntD += 1
        res += cntA * cntD
    elif s < 0:
        i += 1
    else:
        j -= 1

print(res)