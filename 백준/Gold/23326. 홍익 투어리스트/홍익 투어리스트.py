import sys
input = sys.stdin.readline

n, q = map(int, input().split())
a = list(map(int, input().split()))

# Fenwick Tree (1-indexed internally)
bit = [0] * (n + 1)

def bit_add(i, delta):
  # i: 0-indexed position
  i += 1
  while i <= n:
    bit[i] += delta
    i += i & -i

def bit_sum(i):
  # prefix sum on [0..i] (i inclusive), i is 0-indexed
  if i < 0:
    return 0
  i += 1
  s = 0
  while i > 0:
    s += bit[i]
    i -= i & -i
  return s

def bit_total():
  return bit_sum(n - 1)

def bit_kth(k):
  # return the smallest index idx (0-indexed) such that prefix_sum(idx) >= k
  # assumes 1 <= k <= bit_total()
  idx = 0
  step = 1 << (n.bit_length())  # highest power of 2 >= n
  while step:
    nxt = idx + step
    if nxt <= n and bit[nxt] < k:
      idx = nxt
      k -= bit[nxt]
    step >>= 1
  # idx is the largest position with prefix < original k (1-indexed)
  return idx  # 1-indexed position where prefix < k
  # caller converts to 0-index by using (idx)

# Initialize BIT
for i, v in enumerate(a):
  if v == 1:
    bit_add(i, 1)

locate = 0
out = []
for _ in range(q):
  cmd = input().split()
  t = int(cmd[0])
  if t == 1:
    i = int(cmd[1]) - 1
    if a[i] == 0:
      a[i] = 1
      bit_add(i, 1)
    else:
      a[i] = 0
      bit_add(i, -1)
  elif t == 2:
    x = int(cmd[1])
    locate = (locate + x) % n
  else:  # t == 3
    total = bit_total()
    if total == 0:
      out.append("-1")
      continue
    # sum of [locate .. n-1]
    pref_before = bit_sum(locate - 1)
    suffix = total - pref_before
    if suffix > 0:
      # first 1 at or after locate is the (pref_before + 1)-th 1 globally
      idx1_based_less = bit_kth(pref_before + 1)  # returns 1-indexed position with prefix < k
      idx0 = idx1_based_less  # convert to 0-index (since idx is the position with prefix < k)
      # distance from locate to idx0
      out.append(str(idx0 - locate))
    else:
      # wrap: first 1 is the 1st globally
      idx1_based_less = bit_kth(1)
      idx0 = idx1_based_less
      out.append(str(n - locate + idx0))

print("\n".join(out))