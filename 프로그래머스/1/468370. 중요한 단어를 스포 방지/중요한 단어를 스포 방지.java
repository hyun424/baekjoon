import java.util.*;

class Solution {
    static class Word {
        String text;
        int start;
        int end;

        Word(String text, int start, int end) {
            this.text = text;
            this.start = start;
            this.end = end;
        }
    }

    public int solution(String message, int[][] spoiler_ranges) {
        List<Word> words = new ArrayList<>();

        int n = message.length();
        int i = 0;

        // 1. 단어와 단어의 시작/끝 인덱스 저장
        while (i < n) {
            if (message.charAt(i) == ' ') {
                i++;
                continue;
            }

            int start = i;

            while (i < n && message.charAt(i) != ' ') {
                i++;
            }

            int end = i - 1;
            String text = message.substring(start, end + 1);

            words.add(new Word(text, start, end));
        }

        Set<String> normalWords = new HashSet<>();
        Set<String> openedSpoilerWords = new HashSet<>();

        boolean[] isSpoilerWord = new boolean[words.size()];

        // 2. 각 단어가 스포 구간과 겹치는지 판별
        for (int w = 0; w < words.size(); w++) {
            Word word = words.get(w);

            for (int[] range : spoiler_ranges) {
                int s = range[0];
                int e = range[1];

                if (word.start <= e && word.end >= s) {
                    isSpoilerWord[w] = true;
                    break;
                }
            }

            if (!isSpoilerWord[w]) {
                normalWords.add(word.text);
            }
        }

        int answer = 0;

        // 3. 스포 구간을 왼쪽부터 클릭한다고 생각하고 처리
        for (int[] range : spoiler_ranges) {
            int s = range[0];
            int e = range[1];

            for (int w = 0; w < words.size(); w++) {
                Word word = words.get(w);

                if (!isSpoilerWord[w]) continue;

                // 이번 스포 구간과 겹치는 단어
                if (word.start <= e && word.end >= s) {
                    if (!normalWords.contains(word.text)
                            && !openedSpoilerWords.contains(word.text)) {
                        answer++;
                    }

                    openedSpoilerWords.add(word.text);
                }
            }
        }

        return answer;
    }
}