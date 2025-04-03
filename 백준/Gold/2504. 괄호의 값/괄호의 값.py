string=input()
stack=[]
res=''
flag=False
for _ in string:
    if _=='(':
        stack.append("(")
        res+=_
    elif _=='[':
        stack.append("[")
        res+=_
    elif _ ==')':
        if len(stack) ==0 or stack[-1]!='(':
            flag=True
            break
        else:
            stack.pop()
            res+=(")*2")
    elif _ ==']':
        if len(stack) ==0 or stack[-1]!='[':
            flag=True
            break
        else:
            stack.pop()
            res+=("]*3")
if flag or len(stack) !=0:
    print(0)
    exit()
else:
    try:
        res=res.replace("()","(1)").replace("[]","(1)")
        res=res.replace("[","(").replace("]",")")
        res=res.replace("2(","2+(").replace("3(","3+(")

        print(eval(res))
    except:
        print(0)