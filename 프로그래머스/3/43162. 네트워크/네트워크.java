class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            answer +=  dfs(i, computers, visited);
        }
        return answer;
    }
    public static int dfs(int i, int[][] computers, boolean[] visited){
        if(visited[i]) return 0;
        visited[i] = true;
        for(int j = 0; j < computers[i].length; j++){
            if(!visited[j] && computers[i][j] == 1){
                dfs(j, computers, visited);
            }
        }
        return 1;
    }
}