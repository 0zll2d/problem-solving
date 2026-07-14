import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        // 1. 약관 매핑
        Map<String, Integer> tm = new HashMap<>();
        
        for(String term : terms) {
            String[] s = term.split(" ");
            tm.put(s[0], Integer.parseInt(s[1]) * 28);
        }
        
        // 2. 오늘 날짜 일수로 계산
        int now = calculatedDate(today);
        
        // 3. 약관 정보 기준으로 유효기간 검사
        for(int i = 0; i < privacies.length; i++) {
            String[] s = privacies[i].split(" ");
            int date = calculatedDate(s[0]);
            String term = s[1];
            int diff = now - date;
            
            if(tm.get(term) <= diff) {
                answer.add(i + 1);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    private int calculatedDate(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));
        
        return day + month * 28 + year * 12 * 28;
    }
}