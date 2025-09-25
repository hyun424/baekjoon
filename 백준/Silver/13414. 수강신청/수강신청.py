import sys
input = sys.stdin.readline


k, l = map(int, input().split())
seen = {}
for _ in range(l):
    sid = input().strip()  
    if sid in seen:
        del seen[sid]       
    seen[sid] = True        

cnt = 0
out = []
for sid in seen.keys():
    out.append(sid)
    cnt += 1
    if cnt == k:
        break

sys.stdout.write("\n".join(out))