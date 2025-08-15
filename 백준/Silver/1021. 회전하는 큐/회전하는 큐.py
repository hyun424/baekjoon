import sys
from collections import deque
input=sys.stdin.readline

n,m=map(int,input().split())
num=list(map(int,input().split()))
queue=deque()
queue.extend(i for i in range(1,n+1))
ans=0
for _ in (num):
    temp=0

    while queue[0]!=_:
        queue.append(queue.popleft())
        temp+=1
    queue.popleft()
    if temp>len(queue)//2:
        temp=len(queue)+1-temp
    ans+=temp
print(ans)    