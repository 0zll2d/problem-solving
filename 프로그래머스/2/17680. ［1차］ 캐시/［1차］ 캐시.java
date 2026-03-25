import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        // 1. 엣지 케이스: 캐시 크기가 0인 경우
        if(cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;

        Set<String> st = new HashSet<>();   // 캐시에 저장된 도시
        Deque<String> dq = new ArrayDeque<>();  // 캐시 교체 알고리즘(LRU)

        for(String city : cities) {
            city = city.toLowerCase();

            if(st.contains(city)) {     // cache hit
                dq.remove(city);    // LRU를 위해 삭제 후 추가
                dq.offer(city);
                answer += 1;
            } else {    // cache miss
                if(dq.size() == cacheSize) {    // 삭제
                    String rm = dq.peek();
                    st.remove(rm);
                    dq.poll();
                }
                st.add(city);
                dq.offer(city);
                answer += 5;
            }
        }

        return answer;
    }
}