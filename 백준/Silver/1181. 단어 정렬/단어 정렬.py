import sys
input=sys.stdin.readline

n=int(input())
word=[]
for i in range(n):
    word.append(input().rstrip())
words=list(set(word))
words.sort()
words.sort(key=len)
print('\n'.join(words))