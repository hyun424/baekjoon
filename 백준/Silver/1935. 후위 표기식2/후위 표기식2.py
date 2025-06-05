import sys
input = sys.stdin.readline

n= int(input())

postfix=input().strip()

num=[]


for i in range(n):
    num.append(int(input()))

stack=[]

for s in postfix:
    if s.isalpha():
        stack.append(num[ord(s)-65])
    else:
        b=stack.pop()
        a=stack.pop()
        if s=='+':
            stack.append(a+b)
        elif s=='-':
            stack.append(a-b)
        elif s=='*':
            stack.append(a*b)
        elif s=='/':
            stack.append(a/b)
print("{:.2f}".format(stack[0]))
