from itertools import product

def solution(users, emoticons):
    discounts = [10, 20, 30, 40]
    best = [0, 0]  # [가입자 수, 매출]

    # 모든 할인율 조합 탐색
    for comb in product(discounts, repeat=len(emoticons)):
        subs, sales = 0, 0

        for user in users:
            min_discount, threshold = user
            total = 0
            for rate, price in zip(comb, emoticons):
                if rate >= min_discount:  # 조건 만족 시 구매
                    total += price * (100 - rate) // 100

            if total >= threshold:
                subs += 1  # 구독
            else:
                sales += total  # 매출

        # 최적 해 갱신
        if subs > best[0] or (subs == best[0] and sales > best[1]):
            best = [subs, sales]

    return best