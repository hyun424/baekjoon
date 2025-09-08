import sys
input=sys.stdin.readline

n,m,k=map(int,input().split())
board=[[0]*m for _ in range(n)]

stickers=[]

for i in range(k):
    r,c=map(int,input().split())
    sticker=[]
    for _ in range(r):
        sticker.append(list(map(int,input().split())))
    stickers.append(sticker)


# 시도 가능한 4가지 회전 상태를 순차로 확인하며 붙이는 함수
def fill(sticker: list) -> None:
    for _ in range(4):
        r, c = len(sticker), len(sticker[0])
        placed = False
        for i in range(n - r + 1):
            for j in range(m - c + 1):
                if canPlace(i, j, sticker):
                    place(i, j, sticker)
                    placed = True
                    break
            if placed:
                break
        if placed:
            return
        # 90도 시계방향 회전 후 다음 시도
        sticker = rotate(sticker)


def canPlace(x: int, y: int, sticker: list) -> bool:
    r, c = len(sticker), len(sticker[0])
    for i in range(r):
        for j in range(c):
            if sticker[i][j] == 1 and board[x + i][y + j] == 1:
                return False
    return True


def place(x: int, y: int, sticker: list) -> None:
    r, c = len(sticker), len(sticker[0])
    for i in range(r):
        for j in range(c):
            if sticker[i][j] == 1:
                board[x + i][y + j] = 1


def rotate(sticker: list) -> list:
    # 90도 시계방향 회전
    r, c = len(sticker), len(sticker[0])
    rotated = [[0] * r for _ in range(c)]
    for i in range(r):
        for j in range(c):
            rotated[j][r - 1 - i] = sticker[i][j]
    return rotated

for sticker in stickers:
    fill(sticker)

# 붙은 칸 수 출력
print(sum(map(sum, board)))
