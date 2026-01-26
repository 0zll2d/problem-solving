import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
        Map<String, Integer> m = new HashMap<>();
        
        for(int i = 0; i < participant.length; i++) {
            m.put(participant[i], m.getOrDefault(participant[i], 0) + 1);
        }
        
        for(int i = 0; i < completion.length; i++) {
            m.put(completion[i], m.get(completion[i]) - 1);
        }
        
        for(String key: m.keySet()) {
            if(m.get(key) != 0) {
                answer = key;
                break;
            }
        }
        
        return answer;
    }
}