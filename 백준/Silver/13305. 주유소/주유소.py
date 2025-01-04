n=int(input())
d=list(map(int,input().split()))
city=list(map(int,input().split()))
lastindex=n-1
res=0
dist=0
#print(min(city[:lastindex]))
#print("min city index", min(city[:lastindex]))
while (dist<sum(d)):
    min_index=city.index(min(city[:lastindex]))
    temp=sum(d[min_index:lastindex])
    res+=temp*city[min_index]# 오일의 최소값* 그 뒤로 남은 거리
    dist+=temp
    lastindex=min_index
    
print(res)


     