import sys
input = sys.stdin.readline

n = int(input())
flowers = []
for _ in range(n):
    sm, sd, em, ed = map(int, input().split())
    
    flowers.append(((sm, sd), (em, ed)))


flowers.sort(key=lambda f: (f[0][0], f[0][1], f[1][0], f[1][1]))

TARGET = (11, 30)  
cur = (3, 1)      
res = 0
idx = 0            

def pick(e, arr, idx):
    
    best = None
    best_end = e
 
    while idx < len(arr) and arr[idx][0] <= e:
        if arr[idx][1] > best_end:
            best_end = arr[idx][1]
            best = arr[idx]
        idx += 1
    return best, idx


while cur <= TARGET:
    candidate, idx = pick(cur, flowers, idx)
    if candidate is None:
        print(0)
        sys.exit(0)
    res += 1
    cur = candidate[1]
    if cur > TARGET:
        print(res)
        sys.exit(0)

print(res)
