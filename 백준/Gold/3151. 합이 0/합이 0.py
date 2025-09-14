import sys

input = sys.stdin.readline
N = int(input())
arr = list(map(int, input().split()))
arr.sort()

res = 0


for i in range(N - 2):
    
    if arr[i] > 0:
        break

    left, right = i + 1, N - 1
    while left < right:
        s = arr[i] + arr[left] + arr[right]

        if s < 0:
            left += 1
        elif s > 0:
            right -= 1
        else:
            
            if arr[left] == arr[right]:
                
                cnt = right - left + 1
                res += cnt * (cnt - 1) // 2
                break
            else:
                
                lval = arr[left]
                lcnt = 1
                while left + 1 < right and arr[left + 1] == lval:
                    left += 1
                    lcnt += 1

                rval = arr[right]
                rcnt = 1
                while right - 1 > left and arr[right - 1] == rval:
                    right -= 1
                    rcnt += 1

                res += lcnt * rcnt
                left += 1
                right -= 1

print(res)