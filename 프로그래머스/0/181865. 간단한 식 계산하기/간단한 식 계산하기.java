class Solution {
    public int solution(String binomial) {
        String[] aob = binomial.trim().split("\\s+");

        int a = Integer.parseInt(aob[0]);
        int b = Integer.parseInt(aob[2]);
        String op = aob[1];

        if (op.equals("+")) {
            return a + b;
        } else if (op.equals("-")) {
            return a - b;
        } else {
            return a * b;
        }
    }
}
