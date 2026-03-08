import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;
        
        // 이름 -> 인덱스
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(enroll[i], i);
        }
        
        // 부모 인덱스 저장, 추천인이 없으면 -1
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = indexMap.get(referral[i]);
            }
        }
        
        int[] profit = new int[n];
        

        for (int i = 0; i < seller.length; i++) {
            int cur = indexMap.get(seller[i]);
            int money = amount[i] * 100;
            
            while (cur != -1 && money > 0) {
                int up = money / 10;         
                int keep = money - up;      
                
                profit[cur] += keep;
                
                cur = parent[cur];
                money = up;
            }
        }
        
        return profit;
    }
}