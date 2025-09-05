import sys
from itertools import combinations

input = sys.stdin.readline

while True:
    nums = list(map(int, input().split()))
    k = nums[0]
    if k == 0:
        break
    arr = nums[1:]
    
    for comb in combinations(arr, 6):
        print(' '.join(map(str, comb)))
    print()