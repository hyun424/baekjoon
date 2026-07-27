import java.util.*;

class Solution {
    private static int[] dx = {1,-1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static boolean visited[][];
    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        visited = new boolean[n][m];
        Queue <int[]> q = new ArrayDeque<>();
        q.offer(new int[]{0,0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            if(x == n - 1 && y == m - 1){
                return maps[n - 1][m - 1];
            }
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if((nx >= 0 && n > nx) && (ny >= 0 && m > ny)){
                    if(!visited[nx][ny] && maps[nx][ny] == 1){
                        visited[nx][ny] = true;
                        maps[nx][ny] = maps[x][y] + 1;
                        q.offer(new int[]{nx, ny});
                    }
                }
            }
        }
        return answer;
    }
}