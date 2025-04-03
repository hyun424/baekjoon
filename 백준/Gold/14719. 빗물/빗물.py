H,W=map(int,input().split())
block=list(map(int,input().split()))



res=0

for i in range(H):
    flag=[0]*W
    temp=-1
    for index,wall in enumerate(block):
        if wall>=H-i:
            flag[index]=1
        else:
            flag[index]=0
    for index, _ in enumerate(flag):
        if _ ==1:
            
            if temp!=-1:
             
                res+=(index-temp-1)
                
            temp=index
         

        
print(res)