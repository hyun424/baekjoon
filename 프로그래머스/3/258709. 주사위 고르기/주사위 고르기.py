from itertools import combinations, product
from bisect import bisect_left

def solution(dice):
    n = len(dice)
    half = n // 2
    max_win = -1
    answer = []
    
    for comb in combinations(range(n), half):
        A = list(comb)
        B = [i for i in range(n) if i not in A]

        A_sums = [sum(p) for p in product(*[dice[i] for i in A])]
        B_sums = [sum(p) for p in product(*[dice[i] for i in B])]

        B_sums.sort()
        win = 0
        for s in A_sums:
            win += bisect_left(B_sums, s)  # A 합 > B 합인 경우 개수 세기

        if win > max_win:
            max_win = win
            answer = A

    return [i+1 for i in answer]  # 문제 요구사항: 1-based index