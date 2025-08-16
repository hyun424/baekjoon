import sys
input=sys.stdin.readline

while True:
    s=input().rstrip()
    if s==".":
        break
    stack=[]
    for ch in s:
        if ch=="[":
            stack.append(ch)
        elif ch=="(":
            stack.append(ch)
        elif ch==")" :
            if stack and stack[-1]=="(":
                stack.pop()
            else:
                stack.append(ch)

        elif ch=="]":
            if stack and stack[-1]=="[":
                stack.pop()
            else:
                stack.append(ch)

    if len(stack)==0:
        print("yes")
    else:
        print("no")





