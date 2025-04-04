max=0
sum=0
for _ in range(10):
    a,b=map(int,input().split())
    sum=sum-a+b
    if sum>max:
        max=sum
print(max)
