def solution(cap, n, deliveries, pickups):
    # 뒤쪽의 0들을 미리 건너뛰는 헬퍼
    def shift_tail(arr, i):
        while i >= 0 and arr[i] == 0:
            i -= 1
        return i

    di = shift_tail(deliveries, n - 1)  # 배달이 남은 가장 뒤 인덱스
    pi = shift_tail(pickups,   n - 1)  # 수거가 남은 가장 뒤 인덱스

    answer = 0
    while di >= 0 or pi >= 0:
        # 이번 라운드에 가야 할 가장 먼 거리(1-index 기준이라 +1)
        far = max(di, pi) + 1
        answer += far * 2

        # 배달: 뒤에서부터 cap만큼 처리
        carry = cap
        while di >= 0 and carry > 0:
            if deliveries[di] == 0:
                di -= 1
                continue
            take = min(carry, deliveries[di])
            deliveries[di] -= take
            carry -= take
            if deliveries[di] == 0:
                di -= 1
        di = shift_tail(deliveries, di)

        # 수거: 뒤에서부터 cap만큼 처리
        carry = cap
        while pi >= 0 and carry > 0:
            if pickups[pi] == 0:
                pi -= 1
                continue
            take = min(carry, pickups[pi])
            pickups[pi] -= take
            carry -= take
            if pickups[pi] == 0:
                pi -= 1
        pi = shift_tail(pickups, pi)

    return answer