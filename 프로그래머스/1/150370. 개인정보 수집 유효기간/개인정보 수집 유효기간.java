import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> m = new HashMap<>();    // 약관 유형별 유효기간(일수)

        for(int i = 0; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            m.put(term[0], Integer.parseInt(term[1]) * 28);
        }

        int[] t = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt).toArray();  // 오늘 날짜 파싱
        int tday = t[0] * 12 * 28 + (t[1] - 1) * 28 + t[2]; // 달 계산 시 -1 하는 이유: 해당 월이 시작하기 전까지의 개월수 계산이 필요하기 때문

        for(int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int[] p = Arrays.stream(privacy[0].split("\\.")).mapToInt(Integer::parseInt).toArray();
            String type = privacy[1];

            int pday = p[0] * 12 * 28 + (p[1] - 1) * 28 + p[2];

            if((tday - pday) >= m.get(type)) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}

