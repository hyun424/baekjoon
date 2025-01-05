cnt = 0  # 감염된 컴퓨터 수를 세기 위한 변수 초기화

def DFS(virus):
    global cnt  # 전역 변수 cnt를 사용
    visited[virus] = 1  # 현재 바이러스에 감염된 컴퓨터를 방문 처리

    for i in network[virus]:  # 현재 컴퓨터와 연결된 모든 컴퓨터를 순회
        if visited[i] == 0:  # 아직 방문하지 않은 컴퓨터라면
            DFS(i)  # 해당 컴퓨터에 대해 재귀적으로 DFS 수행
            cnt += 1  # 감염된 컴퓨터 수 증가

def BFS(virus):
    global cnt  # 전역 변수 cnt를 사용
    visited[virus] = 1  # 시작 컴퓨터를 방문 처리
    queue = [virus]  # BFS를 위한 큐 초기화

    while queue:  # 큐가 빌 때까지 반복
        current = queue.pop()  # 큐에서 현재 컴퓨터를 꺼냄
        for i in network[current]:  # 현재 컴퓨터와 연결된 모든 컴퓨터를 순회
            if visited[i] == 0:  # 아직 방문하지 않은 컴퓨터라면
                visited[i] = 1  # 해당 컴퓨터를 방문 처리
                queue.insert(0, i)  # 큐의 앞에 추가하여 BFS 순서 유지
                cnt += 1  # 감염된 컴퓨터 수 증가

################ MAIN ###############
N = int(input())  # 전체 컴퓨터의 수 입력
link = int(input())  # 연결된 컴퓨터 쌍의 수 입력

# 각 컴퓨터의 연결 정보를 저장할 리스트 초기화
network = [[] for _ in range(N + 1)]

for _ in range(link):
    a, b = map(int, input().split())  # 두 컴퓨터의 연결 정보 입력
    network[a].append(b)  # a 컴퓨터와 연결된 b 컴퓨터 추가
    network[b].append(a)  # b 컴퓨터와 연결된 a 컴퓨터 추가

visited = [0] * (N + 1)  # 각 컴퓨터의 방문 여부를 저장할 리스트 초기화
BFS(1)  # 1번 컴퓨터부터 BFS 수행
# DFS(1)  # 1번 컴퓨터부터 DFS 수행 (현재 주석 처리됨)
print(cnt)  # 감염된 컴퓨터 수 출력
