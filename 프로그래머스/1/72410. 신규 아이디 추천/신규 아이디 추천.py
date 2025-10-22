def solution(new_id):
    # 1단계: 소문자로 치환
    new_id = new_id.lower()

    # 2단계: 허용 문자만 남기기
    ch = ['-', '_', '.']
    temp = ''
    for c in new_id:
        if c.isalnum() or c in ch:
            temp += c

    # 3단계: 마침표(.)가 2번 이상 연속된 부분을 하나로 치환
    while '..' in temp:
        temp = temp.replace('..', '.')

    # 4단계: 처음이나 끝의 마침표 제거
    temp = temp.strip('.')

    # 5단계: 빈 문자열이면 'a' 대입
    if temp == '':
        temp = 'a'

    # 6단계: 16자 이상이면 15자까지만, 마지막이 '.'이면 제거
    temp = temp[:15]
    temp = temp.rstrip('.')

    # 7단계: 길이가 2 이하라면 마지막 문자를 길이 3이 될 때까지 반복
    while len(temp) < 3:
        temp += temp[-1]

    return temp