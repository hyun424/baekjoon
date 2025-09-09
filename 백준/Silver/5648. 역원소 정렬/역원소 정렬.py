import sys
input = sys.stdin.readline

nums = list(map(int, sys.stdin.read().split()))
n = nums[0]
arr = nums[1:]
# 각 숫자를 뒤집어서 정수로 반환

def rev(x: int) -> int:
    if x == 0:
        return 0
    r = 0
    while x > 0:
        r = r * 10 + (x % 10)
        x //= 10
    return r

# n개만 처리 (입력이 여러 줄/공백 섞여도 안전)
ans = [rev(x) for x in arr[:n]]
ans.sort()
print("\n".join(map(str, ans)))
