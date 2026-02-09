import java.util.*;

class Solution {
    public int[] solution(String x) {
        int[] answer = {0, 0};
        
        while(x.length() > 1) {
            // 1. x의 모든 0 제거
            String tmp = x.replaceAll("0", "");
            
            // 2. 0 제거 후 길이(c)와 제거할 0의 개수 구하기
            int rm = x.length() - tmp.length(); // 제거할 0의 개수
            int c = x.length() - rm;
            answer[1] += rm;
            
            // 3. c를 2진수 문자열로 변환
            x = Integer.toString(c, 2);
            answer[0]++;
        }
        
        return answer;
    }
}