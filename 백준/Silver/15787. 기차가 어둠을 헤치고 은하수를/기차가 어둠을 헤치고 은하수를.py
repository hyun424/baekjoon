n,m=map(int,input().split())
train=[[0]*20 for i in range(n)]
orders=[]
for i in range(m):
    orders.append(list(map(int,input().split())))
for order in orders:
    if order[0]==1:
        train[order[1]-1][order[2]-1]=1
    elif order[0]==2:
        train[order[1]-1][order[2]-1]=0

    elif order[0]==3:
        train[order[1]-1]=[0]+train[order[1]-1][:19]

    else:
        train[order[1]-1]=train[order[1]-1][1:]+[0]
    #print(order)

cnt = 0
state=[]
for i in range(n):
    if train[i] not in state:
        state.append(train[i])
        cnt += 1

print(cnt)