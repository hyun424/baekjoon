class Solution {
    public String solution(String s) {
        String[] words = s.split(" ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        
        for(String word : words){
            int number = Integer.parseInt(word);
            
            min = Math.min(min, number);
            max = Math.max(max, number);
            
        }
        return min + " " + max;
    }
}