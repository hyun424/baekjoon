import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        ArrayList<Character> stack = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == ')' && !stack.isEmpty() && stack.get(stack.size() - 1) == '('){
                stack.remove(stack.size() - 1);
            }
            else{
                stack.add(c);
            }
        }
        if (stack.size() != 0){
            answer = false;
        }

        return answer;
    }
}