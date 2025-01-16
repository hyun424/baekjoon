import sys
input=sys.stdin.readline
n=int(input())
def fib(i):
    if i==0:
        return 0
    elif i==1:
        return 1
    return fib(i-1)+fib(i-2) 

print(fib(n))