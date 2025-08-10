import sys
from collections import Counter

input = sys.stdin.readline

T = int(input())
for _ in range(T):
    a, b = input().split()
    print("Possible" if Counter(a) == Counter(b) else "Impossible")