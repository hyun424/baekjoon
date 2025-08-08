import sys
input = sys.stdin.readline
 
n, m = map(int, input().split())
nums = list(map(int, input().split()))
nums.sort()
 
res = []
visited = [False] * n   
def backtrack(depth: int) -> None:
    if depth == m:
        print(' '.join(map(str, res)))
        return
 
    for i in range(n):
        if not visited[i]:            
            visited[i] = True         
            res.append(nums[i])
            backtrack(depth + 1)
            res.pop()
            visited[i] = False      
 
backtrack(0)