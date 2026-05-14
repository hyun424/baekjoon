import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> stack = new ArrayList<>();
        for(Integer n : arr){
            if(stack.isEmpty() || stack.get(stack.size() - 1) != n){
                stack.add(n);
            }
        }
    int[] answer = new int[stack.size()];
    for(int i = 0; i < stack.size(); i++){
        answer[i] = stack.get(i);
    }
        return answer;
    }
}