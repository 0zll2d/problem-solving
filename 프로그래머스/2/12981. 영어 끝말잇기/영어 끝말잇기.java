import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};

        Set<String> st = new HashSet<>();
        st.add(words[0]);

        for(int i = 1; i < words.length; i++) {
            // 1. 끝말잇기 가능한지 확인
            if((words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0))|| st.contains(words[i])) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }

            st.add(words[i]);
        }

        return answer;
    }
}