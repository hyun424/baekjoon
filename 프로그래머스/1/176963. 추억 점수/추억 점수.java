import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {

        HashMap<String, Integer> score = new HashMap<>();
        int n = yearning.length;
        for(int i = 0; i < n; i++){
            score.put(name[i], yearning[i]);
        }
        int m = photo.length;
        int[] answer = new int[m];
        for(int i = 0; i < m; i++){
            for(String person : photo[i]){
                answer[i] += score.getOrDefault(person,0);
            }
        }
        
        return answer;
    }
}