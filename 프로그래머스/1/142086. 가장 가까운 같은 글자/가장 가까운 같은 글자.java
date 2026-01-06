import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        int[] idx = new int[26];
        
        Arrays.fill(idx, -1);
        
        for(int i = 0; i < s.length(); i++) {
            int alpha = s.charAt(i) - 'a';
            
            if(idx[alpha] != -1) {
                answer[i] = i - idx[alpha];
            } else {
                answer[i] = idx[alpha];
            }
            
            idx[alpha] = i;
        }
        
        return answer;
    }
}