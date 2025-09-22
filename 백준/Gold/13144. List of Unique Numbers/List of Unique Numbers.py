import sys
input = sys.stdin.readline

n = int(input())
arr = list(map(int, input().split()))

res = 0
freq = {}
left = 0
right = 0

while left < n:
    
    while right < n and freq.get(arr[right], 0) == 0:
        freq[arr[right]] = 1
        right += 1

    res += (right - left)

    freq[arr[left]] -= 1
    if freq[arr[left]] == 0:
        del freq[arr[left]]
    left += 1

print(res)