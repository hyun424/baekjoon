n=int(input())
for _ in range(n):
    a,b,i=0,0,0
    string=input()
    if string[0]==')' or string[-1]=='(':
        print("NO")
    else:
            for _ in string:
                if(a<0):
                    break
                else:
                    if(_=='('):
                        a+=1
                    else:
                        a-=1
            if(a!=0):
                print("NO")
            else:
                print("YES")

