import java.util.*;

class Solution {

    public long solution(String expression) {

        List<String> tokens = tokenize(expression);

        String[][] orders = {
                {"+", "-", "*"},
                {"+", "*", "-"},
                {"-", "+", "*"},
                {"-", "*", "+"},
                {"*", "+", "-"},
                {"*", "-", "+"}
        };

        long ans = 0;
        for (String[] order : orders) {
            long val = Math.abs(evaluate(tokens, order));
            ans = Math.max(ans, val);
        }
        return ans;
    }

    // 1) 토큰화: 숫자/연산자 분리
    private List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) num.append(ch);
            else {
                tokens.add(num.toString());
                num.setLength(0);
                tokens.add(String.valueOf(ch));
            }
        }
        tokens.add(num.toString());
        return tokens;
    }

    // 2) 우선순위대로 3번 축약
    private long evaluate(List<String> origin, String[] order) {
        List<String> cur = new ArrayList<>(origin);

        for (String op : order) {
            cur = collapse(cur, op);
        }
        return Long.parseLong(cur.get(0));
    }

    // 3) 특정 연산자(op)만 처리해서 리스트를 축약
    private List<String> collapse(List<String> tokens, String op) {
        List<String> out = new ArrayList<>();
        out.add(tokens.get(0)); // 첫 숫자

        for (int i = 1; i < tokens.size(); i += 2) {
            String operator = tokens.get(i);
            String right = tokens.get(i + 1);

            if (operator.equals(op)) {
                long leftVal = Long.parseLong(out.remove(out.size() - 1));
                long rightVal = Long.parseLong(right);
                long res = calc(leftVal, rightVal, operator);
                out.add(String.valueOf(res));
            } else {
                out.add(operator);
                out.add(right);
            }
        }
        return out;
    }

    private long calc(long a, long b, String op) {
        if (op.equals("+")) return a + b;
        if (op.equals("-")) return a - b;
        return a * b; // "*"
    }
}