n=int(input())
card1=list(map(int,input().split()))
m=int(input())
card2=list(map(int,input().split()))
carddict={}
for i in range(len(card1)):
    carddict[card1[i]]=0
for j in range(m):
    if card2[j] not in carddict:
        print(0,end=' ')
    else:
        print(1, end=' ')