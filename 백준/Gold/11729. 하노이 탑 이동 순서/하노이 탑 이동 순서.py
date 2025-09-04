n=int(input())

def hanoi(h,start,end):
    if h==1:
        print(start,end)
        return
    else:
        hanoi(h-1,start,6-start-end)
        print(start,end)
        hanoi(h-1,6-start-end,end)

print(2**n-1)
hanoi(n,1,3)