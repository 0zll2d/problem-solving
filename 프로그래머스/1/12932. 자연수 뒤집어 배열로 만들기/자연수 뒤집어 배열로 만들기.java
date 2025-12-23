import java.util.*;

class Solution {
    public int[] solution(long n) {
        // StringBuilder 사용해 문자열 뒤집기
        StringBuilder sb = new StringBuilder(new String(String.valueOf(n)));
        sb.reverse();
        
        String s = sb.toString();
        int[] answer = new int[s.length()];
        
        for(int i = 0; i < s.length(); i++) {
            answer[i] = s.charAt(i) - '0';
        }

        return answer;
    }
}

// [ Java 길이 조회 방식 비교 ]
// 배열 (int[], char[] 등)   : length   → 필드
// 문자열 (String)           : length() → 메서드
// 컬렉션 (List, Set, Map)   : size()   → 메서드