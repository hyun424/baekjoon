dp=['-']
space=[' ']
for i in range(12):
    dp=dp+space+dp
    space=space*3

def ans(n):
    for s in dp[0:3**n]:
        print(s,end='')
    print()
num=[]
while(True):
    try:
        n=input()
        ans(int(n))
    except:
        break

