s1=input()
s2=input()
d1,d2=[0]*26,[0]*26
for _ in s1:
    d1[ord(_)-97]+=1

for _ in s2:
    d2[ord(_)-97]+=1
res=0
for i in range(26):
    res+=abs(d1[i]-d2[i])
print(res)