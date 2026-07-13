import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        int[] answer = new int[N];  // 이용자별 처리 결과 메일 받은 횟수
        
        Map<String, Integer> info = new HashMap<>();
        
        for(int i = 0; i < N; i++) {
            info.put(id_list[i], i);
        }
        
        // 동일한 신고 report는 제거
        Set<String> unique = new HashSet<>();
        
        for(String r : report) {
            unique.add(r);
        }
        
        
        int[] reported = new int[N];   // 이용자별 신고 당한 횟수
        
        for(String u : unique) {
            String[] ids = u.split(" ");
            reported[info.get(ids[1])]++;
        }
        
        for(String u : unique) {
            String[] ids = u.split(" ");
            if(reported[info.get(ids[1])] >= k) {
                answer[info.get(ids[0])]++;
            }
        }
        
        return answer;
    }
}