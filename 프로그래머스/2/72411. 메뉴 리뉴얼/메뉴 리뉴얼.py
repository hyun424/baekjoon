from itertools import combinations
from collections import Counter

def solution(orders, course):
    answer = []
    
    for c in course:
        comb_list = []
        
        for order in orders:
            order = ''.join(sorted(order))
            comb_list += combinations(order, c)
        
        counter = Counter(comb_list)
        
        if not counter:
            continue
        
        max_count = max(counter.values())
        
        if max_count >= 2:
            for k, v in counter.items():
                if v == max_count:
                    answer.append(''.join(k))
    
    return sorted(answer)