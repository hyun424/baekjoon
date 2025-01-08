stu=[]
for i in range(28):
    stu.append(int(input()))

stu.sort()
res=[]
for i in range(1,31):
    res.append(i)

res=list(set(res)-set(stu))
res.sort()
for _ in res:
    print(_)