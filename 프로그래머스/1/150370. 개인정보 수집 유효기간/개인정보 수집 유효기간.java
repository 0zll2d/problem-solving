import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();
        
        Map<Character, Integer> m = new HashMap<>();    // 약관 유형별 유효기간(일수)

        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            int days = Integer.parseInt(term[1]) * 28;  // 유효기간(달 -> 일로 변경)
            m.put(term[0].charAt(0), days);
        }

        for(int i = 0; i < privacies.length; i++) {
            int[] t = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();  // 오늘 날짜 파싱
            String[] priv = privacies[i].split(" ");
            int[] p = Arrays.stream(priv[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
            char type = priv[1].charAt(0);

            int diff = 0;
            // 일자 계산
            if(t[2] - p[2] < 0) {
                t[1] -= 1;
                t[2] += 28;
            }
            diff += t[2] - p[2];

            // 월자 계산
            if(t[1] - p[1] < 0) {
                t[0] -= 1;
                t[1] += 12;
            }
            diff += (t[1] - p[1]) * 28;
            // 년도 계산
            diff += (t[0] - p[0]) * 28 * 12;

            if(diff >= m.get(type)) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
// 정규표현식에서 '.'의 의미 = 모든 문자
// 점(.) 문자 그대로 사용하기 위해 escape 필요 '.' -> '\\.'

