T=int(input())

def rotate(graph):
    a,b,c,d=[],[],[],[]
    n=len(graph)
    for i in range(n):
        a.append(graph[(n)//2][i])#가로
        b.append(graph[i][i])#우하강
        c.append(graph[i][(n)//2])#세로
        d.append(graph[i][n-1-i])#좌하강
    for i in range(n):
        graph[(n)//2][i]=d[n-1-i]
        graph[i][i]=a[i]
        graph[i][(n)//2]=b[i]
        graph[i][n-1-i]=c[i]

graph=[]

for _ in range(T):
    n,angle=map(int,input().split())

    for i in range(n):
        graph.append(list(map(int,input().split())))
    #print(graph)
    if angle<0:
        angle+=360

    for j in range(angle//45):
        rotate(graph)
    #print("graph")
    for row in graph:
        for num in row:
            print(num,end=' ')
        print()
    graph=[]
