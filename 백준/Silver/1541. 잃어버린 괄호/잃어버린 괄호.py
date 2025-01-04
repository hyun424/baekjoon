s=input()
res=''
flag=0
temp=''
for _ in s:
    #print(res,_,flag)
    if (_=='-' and flag==0):
        res+=str(int(temp))
        temp=''
        res+=_
        res+='('
        flag=1
    elif(_=='-' and flag==1):
        res+=str(int(temp))
        temp=''
        res+=')'
        res+=_
        res+='('
        flag=1
    elif(_=='+'):
        res+=str(int(temp))
        temp=''
        res+=_
    else:
        temp+=_

if(temp!=''):
    res+=str(int(temp))
    temp=''

if(flag==1):
    res+=')'
#print("res",res,"temp", temp)
print(eval(res))
