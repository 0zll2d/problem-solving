import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        int[] answer = new int[N];

        // 1. 신고 중복 제거
        Set<String> st = new HashSet<>();
        for(String rep: report) {
            st.add(rep);
        }

        // 2. 사용자 ID와 인덱스 매핑
        // 3. 신고 당한 사람 기준으로 신고자 ID 리스트 매핑
        Map<String, Integer> m = new HashMap<>();
        List<List<String>> report_list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            m.put(id_list[i], i);
            report_list.add(new ArrayList<>());
        }

        for(String s : st) {
            String[] ids = s.split(" ");
            report_list.get(m.get(ids[1])).add(ids[0]); // 신고 당한 사람 기준으로 신고자 리스트 생성
        }

        // 4. 이용 정지 확인 및 신고자에게 메일 발송
        for(int i = 0; i < N; i++) {
            if(report_list.get(i).size() >= k) {
                for(int j = 0; j < report_list.get(i).size(); j++) {
                    answer[m.get(report_list.get(i).get(j))]++;
                }
            }
        }

        return answer;
    }
}