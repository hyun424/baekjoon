class Solution {
    public int solution(int n, int w, int num) {
        int floor = (num - 1) / w;
        int pos = (num - 1) % w;
        
        int col;
        if (floor % 2 == 0) col = pos;
        else col = w - 1 - pos;
        
        int totalFloor = (n - 1) / w;
        int answer = 0;
        
        for (int r = floor; r <= totalFloor; r++) {
            int box;
            
            if (r % 2 == 0) box = r * w + col + 1;
            else box = r * w + (w - col);
            
            if (box <= n) answer++;
        }
        
        return answer;
    }
}