import sys
input=  sys.stdin.readline

graph=[]
res=0
for i in range(8):
    line=list(input().rstrip())
    for idx,s in enumerate(line):
        if i%2==0:
            if idx%2==0 and s=='F':
                res+=1
        else:
            if idx%2==1 and s=='F':
                res+=1

print(res)
