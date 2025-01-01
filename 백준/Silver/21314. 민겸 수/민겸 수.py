s=input()
min=''
max=''
s1=s[::-1]
#최대 최소 구하는  법?
#m일때 1 k일때 5 mm이면 10 최소
#최대일때는 ? 
#m 다음 m이면 무조건 배수 높히기 ?
#mmmmm이면 어떡해 그럼 11111가 더큰데
temp=''
flag=0
for index,_ in enumerate (s1):
    if _ =='K':
        min+='5'
    else:
        if index==(len(s1)-1):#마지막 문자열 일 때
            min+=(str(10**flag))[::-1]
        elif(s1[index+1]==_):   #마지막 문자열이 아니고 M다음 M이 올때 
            flag+=1
        else:
            min+=(str(10**flag))[::-1]  #마지막 문자열이 아니고 M 다음 K가 올 때
            flag=0
min=min[::-1]
flag=0
temp=''
#m다음 k 가 올 때 /m 다음 m이 올 때 /k 다음 m 이 올 때 /k 다음 k가 올 떄 /마지막 문자열 일 때 
for index, _ in enumerate (s):
    if _ =='K':
        if flag==0:
            max+='5'
        else:
            max+=str(5*(10**flag))
            flag=0
    else:
        flag+=1
        if (index==len(s)-1):
            for i in range(flag):
                max+='1'
            flag=0


print(max)

print(min)            