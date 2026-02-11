class Solution {
    public String solution(String my_string, int s, int e) {

        String front = my_string.substring(0, s);
        String mid = my_string.substring(s, e + 1);
        String back = my_string.substring(e + 1);

        String reversed = new StringBuilder(mid).reverse().toString();

        return front + reversed + back;
    }
}
