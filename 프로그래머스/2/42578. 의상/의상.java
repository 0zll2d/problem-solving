import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        Map<String, Integer> m = new HashMap<>();
        
        for(String[] clothe : clothes) {
            String type = clothe[1];
            m.put(type, m.getOrDefault(type, 0) + 1);
        }
        
        for(String key : m.keySet()) {
            answer *= (m.get(key) + 1);
        }
        
        return answer - 1;
    }
}