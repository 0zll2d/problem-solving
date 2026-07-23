import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        
        int N = progresses.length;
        
        int[] days = new int[N];
        
        for(int i = 0; i < N; i++) {
            int day = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0) {    // 올림(예: 95%, 개발 속도 4% = 2일)
                day++;
            }
            days[i] = day;
        }
        
        Deque<Integer> stk = new ArrayDeque<>();
        
        // 예: 7 3 9 
        for(int day : days) {
            // 7과 9는 스택에 추가
            if(stk.isEmpty() || stk.peek() < day) {
                stk.push(day);
                answer.add(1);
            } else {    // 3은 7에 합쳐 기능 배포
                int idx = answer.size() - 1;
                answer.set(idx, answer.get(idx) + 1);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}