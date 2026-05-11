import java.util.*;

class Solution {
    Map<String, Integer> getMultiSet(String str) {
        Map<String, Integer> m = new HashMap<>();   // 다중집합 합집합

        for(int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);

            if(Character.isLetter(c1) && Character.isLetter(c2)) {
                String attr = new StringBuilder()
                    .append(c1)
                    .append(c2)
                    .toString();

                m.put(attr, m.getOrDefault(attr, 0) + 1);
            }
        }

        return m;
    }
    
    public int solution(String str1, String str2) {
        int answer = 65536;
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        Map<String, Integer> m1 = getMultiSet(str1);    // A 다중집합
        Map<String, Integer> m2 = getMultiSet(str2);    // B 다중집합
        
        if(m1.size() == 0 && m2.size() == 0) {
            return answer;
        }
        
        int intersect = 0;
        int union = 0;
        
        for(String key : m1.keySet()) {
            int v1 = m1.get(key);
            
            // 다중집합의 교집합과 합집합 구하기
            if(m2.containsKey(key)) {
                int v2 = m2.get(key);
                intersect += Math.min(v1, v2);
                union += Math.max(v1, v2);
            } else {
                union += v1;
            }
        }
        
        for(String key : m2.keySet()) {
            int v2 = m2.get(key);
            
            // 교집합이면 생략
            if(m1.containsKey(key)) {
                continue;
            }
            
            union += v2;
        }
        
        return answer * intersect / union;
    }
}