import sys
input=sys.stdin.readline

site={}
n,m=map(int,input().split())
for i in range(n):
    url,pasword=input().split()
    site[url]=pasword
for i in range(m):
    url=input().strip()
    print(site[url])

        