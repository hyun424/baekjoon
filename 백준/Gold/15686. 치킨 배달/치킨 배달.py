import sys
from collections import deque
from itertools import combinations
input = sys.stdin.readline

n,m=map(int,input().split())
b=[list(map(int,input().split())) for _ in range(n)]

'''
1. 치킨 거리의 합을 구하는 함수
2. m개의 치킨집을 고르는 합수
'''
home=[]
chicken=[]
for i in range(n):
    for j in range(n):
        if b[i][j]==1:
            home.append((i,j))
        elif b[i][j]==2:
            chicken.append((i,j))



def dist(selected_chicken:list,home:list)->int:
    
    res=0
    
    for h in home:
        min=2*(n+1)
        for c in selected_chicken:
            temp=abs(h[0]-c[0])+abs(h[1]-c[1])
            if temp<min:
                min=temp
        res+=min
    
    return res
#여기서는 선택된 치킨집의 리스트를 반환하고싶은데 
res=[]
for c in combinations(chicken,m):
    res.append(dist(c,home))

print(min(res))  
        







