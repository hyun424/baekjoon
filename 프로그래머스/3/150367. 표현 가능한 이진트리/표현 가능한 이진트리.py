def solution(numbers):
    def to_full_binary(n: int) -> str:
        b = bin(n)[2:]
        # 2^h - 1 >= len(b) 인 최소 h 찾기
        length = 1
        while length < len(b):
            length = length * 2 + 1  # 1,3,7,15,...
        return b.zfill(length)

    def is_valid(tree: str) -> bool:
        # tree 길이는 항상 2^h - 1
        if len(tree) == 1:
            return True
        mid = len(tree) // 2
        root = tree[mid]
        left, right = tree[:mid], tree[mid+1:]
        # 루트가 0인데 서브트리에 1이 있으면 불가
        if root == '0' and ('1' in left or '1' in right):
            return False
        # 좌우 서브트리도 같은 규칙으로 검사
        return is_valid(left) and is_valid(right)

    answer = []
    for n in numbers:
        fb = to_full_binary(n)
        answer.append(1 if is_valid(fb) else 0)
    return answer