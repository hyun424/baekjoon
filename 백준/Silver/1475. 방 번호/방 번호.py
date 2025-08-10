n = input() # 다솜이의 방 번호

array = [0] * 10 # 각 숫자의 개수를 셀 list

for i in n:
    if i == '9' or i == '6': # 9 혹은 6이라면
        if array[9] <= array[6]: # 9의 개수가 더 작다면
            array[9] += 1        # 9의 개수를 count
        else:                    # 6의 개수가 더 작다면
            array[6] += 1        # 6의 개수를 count
    else:                    # 9 혹은 6이 아니라면
        array[int(i)] += 1   # 숫자의 개수를 세어줌

print(max(array)) # 개수 중 최대값을 출력