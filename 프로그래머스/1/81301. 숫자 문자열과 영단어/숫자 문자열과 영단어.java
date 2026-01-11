import java.util.*;

class Solution {
    public int solution(String s) {
        Map<String, String> m = new HashMap<>();
        m.put("zero", "0");
        m.put("one", "1");
        m.put("two", "2");
        m.put("three", "3");
        m.put("four", "4");
        m.put("five", "5");
        m.put("six", "6");
        m.put("seven", "7");
        m.put("eight", "8");
        m.put("nine", "9");
        
        for(String key : m.keySet()) {
            s = s.replaceAll(key, m.get(key));
        }
        
        return Integer.parseInt(s);
    }
}