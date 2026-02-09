import java.util.*;

class Solution {
    public String[] solution(String[] todo_list, boolean[] finished) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < finished.length; i++) {
            if (!finished[i]) {   // 아직 안 끝났으면
                list.add(todo_list[i]);
            }
        }

        return list.toArray(new String[0]);
    }
}
