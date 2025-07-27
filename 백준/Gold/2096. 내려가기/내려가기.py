import sys
input = sys.stdin.readline

n=int(input())


max_past=[0,0,0]
min_past=[0,0,0]
max_crrunt=[0,0,0]
min_crrunt=[0,0,0]
for i in range(n):
    crrunt = list(map(int, input().split()))
    
    max_crrunt=[max(max_past[0], max_past[1]) + crrunt[0],
                max(max_past[0], max_past[1], max_past[2]) + crrunt[1],
                max(max_past[1], max_past[2]) + crrunt[2]]
    
    max_past=max_crrunt

    min_crrunt=[min(min_past[0], min_past[1]) + crrunt[0],
                min(min_past[0], min_past[1], min_past[2]) + crrunt[1],
                min(min_past[1], min_past[2]) + crrunt[2]]

    min_past=min_crrunt

print(max(max_past), min(min_past))