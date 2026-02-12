class Solution {
    public String solution(String code) {
        StringBuilder ret = new StringBuilder();
        int mode = 0;
        
        for (int i = 0; i < code.length(); i++) {
            char now = code.charAt(i);
            
            if (now == '1') {
                mode = 1 - mode;  // 토글
            } else {
                if (mode == 0 && i % 2 == 0) {
                    ret.append(now);
                } else if (mode == 1 && i % 2 == 1) {
                    ret.append(now);
                }
            }
        }
        
        if (ret.length() == 0) {
            return "EMPTY";
        }
        
        return ret.toString();
    }
}
