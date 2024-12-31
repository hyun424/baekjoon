n=int(input())

if (n==1 or n==3):
    print(-1)
    
else:
    if(n%5==0):
        res=n//5
    
    else:
        if ((n%5)%2==0):
            res=(n//5)+((n%5)//2)
        else:
            res=(n//5)-1+(((n%5)+5)//2)

    print(res)