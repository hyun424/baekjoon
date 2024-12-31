n=int(input())
ropes=[]
for i in range(n):
    ropes.append(int(input()))

ropes.sort()
max=0
for index,rope in enumerate (ropes):
    temp=(ropes[index])*(len(ropes)-index)
    if max<=temp:
        max=temp

print(max)
