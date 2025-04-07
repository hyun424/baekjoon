n=int(input())

map=[[] for _ in range(n)]

for i in range(n):
    map[i]=list(input())
# print(map)
# C P Z Y
def check(map):
    max=1
    cnt1=1
    cnt2=1
    n=len(map)

    for i in range(n):
        for j in range(n):
            if j<n-1:# j<3
                if map[i][j]==map[i][j+1]:
                    
                    cnt1+=1
                    if max<=cnt1:
                        max=cnt1
                else:
                    cnt1=1
            

                if map[j][i]==map[j+1][i]:
                    cnt2+=1
                    if max<=cnt2:
                        max=cnt2 
                else:
                    cnt2=1
            else:
                cnt1,cnt2=1,1

    return max




# print(map[0][0])

#바꾸는건 한번만 바꾸면 됨 바꾸는 모든경우의 수 + 바꾼이후에 최대 몇개인지 검사하는코드

def solve(map):
    max=0
    for i in range(n):
        for j in range(n):
            if i<=n-2 and j <=n-2:
                map[i][j],map[i][j+1]=map[i][j+1],map[i][j]
                
                #최대 개수 확인
                if max<=check(map):
                    max=check(map)

                #되돌리기
                map[i][j],map[i][j+1]=map[i][j+1],map[i][j]



                map[i][j],map[i+1][j]=map[i+1][j],map[i][j]
                if max<=check(map):
                    max=check(map)

                #되돌리기
                map[i][j],map[i+1][j]=map[i+1][j],map[i][j]

            else:
                if i==n-1 and j<=n-2:

                    map[i][j],map[i][j+1]=map[i][j+1],map[i][j]
                    if max<=check(map):
                        max=check(map)

                    map[i][j],map[i][j+1]=map[i][j+1],map[i][j]

                elif i<=n-2 and j==n-1:
                    map[i][j],map[i+1][j]=map[i+1][j],map[i][j]
                    if max<=check(map):
                        max=check(map)

                    map[i][j],map[i+1][j]=map[i+1][j],map[i][j]

                else:
                    break
    return max
#print(check(map))
print(solve(map))