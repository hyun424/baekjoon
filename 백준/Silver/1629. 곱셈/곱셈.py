import sys
input = sys.stdin.readline

a, b, c = map(int, input().split())

def mod_pow(a: int, b: int, m: int) -> int:
    a %= m
    res = 1
    while b > 0:
        if b & 1:
            res = (res * a) % m
        a = (a * a) % m
        b //= 2
    return res

print(mod_pow(a, b, c))