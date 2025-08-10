s=input()
d=[0]*26

for _ in s:
    d[ord(_)-97]+=1

for _ in d:
    print(_,end=' ')