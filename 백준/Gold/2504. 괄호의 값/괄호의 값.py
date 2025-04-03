
arr=input()
stack=[]

answer=0
tmp=1
for i in range(len(arr)):
    if arr[i] =='(':
        stack.append(arr[i])
        tmp *=2
    elif arr[i] == '[':
        stack.append(arr[i])
        tmp *=3
    elif arr[i] == ")":
        if not stack or stack[-1] == "[":
            answer = 0 # 실패
            break
        if arr[i-1] == "(":
            answer += tmp
        stack.pop()
        tmp //= 2  #tmp 초기화
    else:
        if not stack or stack[-1] == "(":
            answer=0
            break
        if arr[i-1] =='[':
            answer+=tmp
        stack.pop()
        tmp //=3 #tmp 초기화

if stack:
    print(0)
else:
    print(answer)


# 큰 괄호 안에 작은 괄호끼리 더해져 있을 때 이를 괄호 두개로 나눠서 생각 
# 작은 괄호 때문에 곱해져 있는거만큼 다시 나눠서 뒤에 있는 작은 괄호에 영향 안주도록 나눔 