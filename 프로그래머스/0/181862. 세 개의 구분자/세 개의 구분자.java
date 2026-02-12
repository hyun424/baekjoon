import java.util.*;
class Solution {
    public String[] solution(String myStr) {

        String[] parts = myStr.split("[abc]");

        List<String> list = new ArrayList<>();
        for (String s : parts) {
            if (!s.isEmpty()) {
                list.add(s);
            }
        }

        if (list.isEmpty()) {
            return new String[]{"EMPTY"};
        }

        return list.toArray(new String[0]);
    }
}
