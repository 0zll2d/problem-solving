import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Map<Integer, Integer> m1 = new HashMap<>(); // 철수
        
        Set<Integer> st1 = new HashSet<>(); // 철수
        Set<Integer> st2 = new HashSet<>(); // 동생
        
        for(int t : topping) {
            m1.put(t, m1.getOrDefault(t, 0) + 1);
            st1.add(t);
        }
        
        for(int t : topping) {
            // 철수에게서 토핑 제거
            m1.put(t, m1.get(t) - 1);
            
            if(m1.get(t) == 0) {
                st1.remove(t);
            }
            
            // 동생에게 토핑 추가
            st2.add(t);
            
            // 공평하게 나뉘었는지 확인
            if(st1.size() == st2.size()) {
                answer++;
            }
        }
        
        return answer;
    }
}