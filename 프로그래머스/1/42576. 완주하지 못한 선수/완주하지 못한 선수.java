import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        
        Arrays.sort(participant);
        Arrays.sort(completion);
        
        for(int i = 0; i < completion.length; i++) {
            // 문자열 비교는 equals
            if(!completion[i].equals(participant[i])) {
                return participant[i];
            }
        }
        
        return participant[completion.length];
    }
}