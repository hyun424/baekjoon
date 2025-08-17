import sys
input=sys.stdin.readline

n=int(input())
res=0
for _ in range(n):
    s=input().rstrip()
    stack=[]
    for ch in s:
        if stack and stack[-1]== ch:
            stack.pop()
        else:
            stack.append(ch)
    if len(stack)==0:
        res+=1
print(res)

    