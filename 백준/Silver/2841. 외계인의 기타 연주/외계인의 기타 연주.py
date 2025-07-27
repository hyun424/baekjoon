import sys
input = sys.stdin.readline

#누르고 있는 줄 + 누고 있는 프렛 중에 가장 높은 음을 냄
n,p=map(int,input().split())
graph=[[0] for _ in range(n+1)]
res=0
for i in range(n):
    
    line, fret = map(int,input().split())

    if max(graph[line]) > fret:

        while(max(graph[line]) > fret):            
            graph[line].pop()
            res+=1
        
    
    if fret not in graph[line]:
        graph[line].append(fret)
        res+=1
print(res)