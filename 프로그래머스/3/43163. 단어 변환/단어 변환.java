import java.util.*;

class Solution {
    boolean[] visited;
    class tempword{
        String word;
        int res;
        tempword(String word, int res){
            this.word = word;
            this.res = res;
        }
    }
    public static boolean canChange(String A, String B){
        int temp = 0;
        for(int i = 0; i < A.length(); i++){
            if(A.charAt(i) != B.charAt(i)) temp++;
        }
        if (temp == 1) return true;
        return false;
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int size = words.length;
        visited = new boolean[size];
        Queue<tempword> q = new ArrayDeque <>();
        
        q.offer(new tempword(begin, 0));
        
        while(!q.isEmpty()){
            
            tempword cur = q.poll(); 
            
            String word = cur.word;
            int res = cur.res;
            
            if (word.equals(target)) return res;
            
            for(int i = 0; i < size; i++){
                
                if(!visited[i] && canChange(word, words[i])){
                    
                    visited[i] = true;
                    q.offer(new tempword(words[i], res + 1));
                }
            }
        }
        return answer;
    }
}