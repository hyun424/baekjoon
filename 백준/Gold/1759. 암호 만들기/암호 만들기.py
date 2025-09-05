import sys
input = sys.stdin.readline

L, C = map(int, input().split())
alphabets = input().split()
alphabets.sort()

vowels = set("aeiou")
path = []

def back(start: int, vowel_cnt: int) -> None:
    if len(path) == L:
        # 모음이 1개 이상, 자음이 2개 이상
        if vowel_cnt >= 1 and (L - vowel_cnt) >= 2:
            print(''.join(path))
        return

    for i in range(start, C):
        ch = alphabets[i]
        path.append(ch)
        back(i + 1, vowel_cnt + (1 if ch in vowels else 0))
        path.pop()

back(0, 0)