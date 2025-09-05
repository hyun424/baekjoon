n=int(input())
graph=[['*']*n for _ in range(n)]
def star(x,y,n):
    if n==1:
        return
    scope=n//3
    for i in range(x+scope,x+2*scope):
        for j in range(y+scope,y+2*scope):
            graph[i][j]=' '
    star(x,y,scope)
    star(x,y+scope,scope)
    star(x,y+2*scope,scope)
    star(x+scope,y,scope)
    star(x+scope,y+2*scope,scope)
    star(x+2*scope,y,scope)
    star(x+2*scope,y+scope,scope)
    star(x+2*scope,y+2*scope,scope)
star(0,0,n)
for _ in graph:
    print(''.join(_))

