import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        // 1. 신고 중복 제거
        Set<String> st = new HashSet<>();
        for(String rep: report) {
            st.add(rep);
        }

        // 2. 유저 ID와 인덱스 매핑
        Map<String, Integer> m = new HashMap<>();
        List<List<String>> report_list = new ArrayList<>();   // 유저 신고한 ID 리스트

        for(int i = 0; i < id_list.length; i++) {
            m.put(id_list[i], i);
            report_list.add(new ArrayList<>());
        }

        // 3. 신고 당한 횟수 세기
        int[] count = new int[id_list.length];

        for(String s : st) {
            String[] ids = s.split(" ");
            count[m.get(ids[1])]++; // 신고 당한 횟수 세기
            report_list.get(m.get(ids[0])).add(ids[1]);
        }

        // 4. 이용 정지 확인하기
        boolean[] suspended = new boolean[id_list.length];

        for(int i = 0; i < id_list.length; i++) {
            if(count[i] >= k) {
                suspended[i] = true;
            }
        }

        // 5. 이용 정지 계정에 대해 신고자에게 메일 발송하기
        for(int i = 0; i < id_list.length; i++) {
            int result = 0; // 신고 결과
            for(int j = 0; j < report_list.get(i).size(); j++) {
                if(suspended[m.get(report_list.get(i).get(j))]) {
                    result++;
                }
            }
            answer[i] = result;
        }

        return answer;
    }
}