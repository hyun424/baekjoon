import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

n = int(input().strip())
eggs = [list(map(int, input().split())) for _ in range(n)]  # [durability, weight]

best = 0

def dfs(i: int) -> None:
    global best

    if i == n:
        broken = sum(1 for s, _ in eggs if s <= 0)
        if broken > best:
            best = broken
        return
    
    if eggs[i][0] <= 0:
        dfs(i + 1)
        return


    hit_done = False
    for j in range(n):
        if j == i or eggs[j][0] <= 0:
            continue
        
        hit_done = True
        eggs[i][0] -= eggs[j][1]
        eggs[j][0] -= eggs[i][1]

        dfs(i + 1)
        

        eggs[j][0] += eggs[i][1]
        eggs[i][0] += eggs[j][1]
        
    if not hit_done:
        dfs(i + 1)


dfs(0)
print(best)
