import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Set<String> reportSet = new HashSet<>(Arrays.asList(report));

        Map<String, Integer> reportedCount = new HashMap<>();
        Map<String, List<String>> reporter = new HashMap<>();

        for (String rep : reportSet) {
            String[] arr = rep.split(" ");
            String reporterId = arr[0];
            String reportedId = arr[1];

            reportedCount.put(reportedId, reportedCount.getOrDefault(reportedId, 0) + 1);

            reporter.computeIfAbsent(reporterId, key -> new ArrayList<>())
                    .add(reportedId);
        }

        Set<String> banned = new HashSet<>();

        for (String id : reportedCount.keySet()) {
            if (reportedCount.get(id) >= k) {
                banned.add(id);
            }
        }

        for (int i = 0; i < id_list.length; i++) {
            String user = id_list[i];

            if (!reporter.containsKey(user)) continue;

            for (String reportedId : reporter.get(user)) {
                if (banned.contains(reportedId)) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }
}