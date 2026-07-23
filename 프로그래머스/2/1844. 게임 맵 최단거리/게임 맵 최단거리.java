import java.util.*;

class Solution {
    int[] dx ={1, -1, 0, 0};
    int[] dy ={0, 0, 1, -1};
    boolean[][] visited;
    public static class Locate{
        int x;
        int y;
        int distance;
        Locate(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance= distance;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        visited = new boolean[n][m];
        int answer = -1;
        Queue <Locate> q = new ArrayDeque<>();
        q.offer(new Locate(0, 0, 1));
        visited[0][0] = true;
        while(!q.isEmpty()){
            Locate cur = q.poll();
            
            if(cur.x == n - 1 && cur.y == m - 1){
                answer = cur.distance;
                return answer;
            }
            
            for (int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if ((0 <= nx && nx < n) && (0 <= ny && ny < m)){
                    if (maps[nx][ny] == 1 && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.offer(new Locate(nx,ny,cur.distance + 1));
                    }
                }
            }
        }
        return answer;
    }
}
/*
0,0에서 n-1 m-1 까지 
*/