class Solution {
    public String solution(String my_string, int[] indices) {
        boolean[] remove = new boolean[my_string.length()];
        
        // 지울 위치 표시
        for (int idx : indices) {
            remove[idx] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        
        // 전체 문자열 순회
        for (int i = 0; i < my_string.length(); i++) {
            if (!remove[i]) {
                sb.append(my_string.charAt(i));
            }
        }
        
        return sb.toString();
    }
}
