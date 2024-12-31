n=int(input())
machine=list((map(int,input().split())))

machine.sort()
machine=sorted(machine)
machine1=machine
machine2=list(reversed(machine))


if n%2==0:
    max=0
    for i in range(n//2):
        temp=machine1[i]+machine2[i]
        if max<=temp:
            max=temp
else:
    max=machine2[0]
    for i in range((n-1)//2):
        temp=machine1[i]+machine2[i+1]
        if max<=temp:
            max=temp    
print(max)