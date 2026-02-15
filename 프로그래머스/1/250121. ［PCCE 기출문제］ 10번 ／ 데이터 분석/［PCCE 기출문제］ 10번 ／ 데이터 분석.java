import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int extIdx = col(ext);
        int sortIdx = col(sort_by);

        List<int[]> list = new ArrayList<>();
        for (int[] row : data) {
            if (row[extIdx] < val_ext) list.add(row);
        }

        list.sort(Comparator.comparingInt(a -> a[sortIdx]));

        return list.toArray(new int[list.size()][]);
    }

    private int col(String s) {
        switch (s) {
            case "code": return 0;
            case "date": return 1;
            case "maximum": return 2;
            case "remain": return 3;
        }
        return -1; // 입력은 보장되므로 여기 오지 않음
    }
}
