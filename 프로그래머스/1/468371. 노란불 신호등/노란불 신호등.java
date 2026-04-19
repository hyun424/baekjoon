class Solution {
    public int solution(int[][] signals) {
        int n = signals.length;
        
        int[] start = new int[n];   // 노란불 시작 위치(0-based)
        int[] end = new int[n];     // 노란불 끝 직전 위치
        int[] cycle = new int[n];   // 주기
        
        int lcmValue = 1;
        
        for (int i = 0; i < n; i++) {
            int g = signals[i][0];
            int y = signals[i][1];
            int r = signals[i][2];
            
            start[i] = g;
            end[i] = g + y;
            cycle[i] = g + y + r;
            
            lcmValue = lcm(lcmValue, cycle[i]);
        }
        
        for (int t = 1; t <= lcmValue; t++) {
            boolean allYellow = true;
            
            for (int i = 0; i < n; i++) {
                int pos = (t - 1) % cycle[i]; // 시간 1초부터 시작
                
                if (pos < start[i] || pos >= end[i]) {
                    allYellow = false;
                    break;
                }
            }
            
            if (allYellow) {
                return t;
            }
        }
        
        return -1;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }
}