import sys
input = sys.stdin.readline

n = int(input())
if n < 2:
    print(0)
    sys.exit(0)


is_prime = [True] * (n + 1)
is_prime[0] = is_prime[1] = False
limit = int(n ** 0.5)
for i in range(2, limit + 1):
    if is_prime[i]:
        step = i
        start = i * i
        is_prime[start:n+1:step] = [False] * (((n - start) // step) + 1)

primes = [i for i, v in enumerate(is_prime) if v]
m = len(primes)


ans = 0
left = 0
right = 0
curr = 0

while True:
    if curr >= n:
        if curr == n:
            ans += 1
      
        curr -= primes[left]
        left += 1
    else:
        if right == m: 
            break
        curr += primes[right]
        right += 1

print(ans)