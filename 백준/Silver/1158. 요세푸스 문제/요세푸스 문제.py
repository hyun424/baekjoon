n, k = map(int, input().split())
p = [i for i in range(1, n+1)]
x = 0
res = []
while p:
    x = (x + k - 1) % len(p)  
    res.append(p.pop(x))
print("<" + ", ".join(map(str, res)) + ">")