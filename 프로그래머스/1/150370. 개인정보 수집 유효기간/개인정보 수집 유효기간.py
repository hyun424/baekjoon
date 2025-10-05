def solution(today, terms, privacies):
    def to_days(s):
        y, m, d = map(int, s.split('.'))
        return y*12*28 + m*28 + d

    
    term = {}
    for t in terms:
        k, v = t.split()
        term[k] = int(v)

    today_days = to_days(today)

    ans = []
    for i, p in enumerate(privacies, 1):
        date_str, t = p.split()
        start = to_days(date_str)
        expire = start + term[t] * 28
        if today_days >= expire:
            ans.append(i)
    return ans