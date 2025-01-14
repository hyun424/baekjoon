import sys 
input=sys.stdin.readline
k,n=map(int,input().split())
#sys.stdin.readline().split()
rope=[]
for i in range(k):
    rope.append(int(input().strip()))
rope.sort()

start=1
end=sum(rope)//n
#최적상태의 길이는 end임 end에서 길이를 줄여 나가면서 최대길이를 찾을거 
#처음 값은 11개 보다 많을 수 있음 11개 이상도 상관 ㄴㄴ 적지만 않으면 됨 

while start<=end:
    mid=(start+end)//2
    lines=0
    for i in rope:
        lines+=i//mid
    if lines>=n:
        start=mid+1
    else:
        end=mid-1
print(end)