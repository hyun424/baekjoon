import sys
input=sys.stdin.readline
n=int(input())
fib=[0,1]+([0]*(n))

def fibo(i):
    for _ in range(2,i+1):
        
        fib[_]=fib[_-1]+fib[_-2]

fibo(n)
print(fib[n])