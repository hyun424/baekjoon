data = list(input())
temp_data=data.copy()
stack=[]
quack=['q','u','a','c','k']
temp=0
res=0
while(True):
    for index,d in enumerate(data):
        if d==quack[temp%5]:
            stack.append(index)
            temp+=1
    if len(stack)%5!=0:
        res=-1
        break
    for _ in stack:
        temp_data[_]=0
    
    if temp_data!=data:
        
        data=temp_data.copy()
        res+=1
    else:
        break
for _ in data:
    if _ != 0:
        res=-1
        break
print(res)



    