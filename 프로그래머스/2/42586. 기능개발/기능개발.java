import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        
        List<Integer> answer = new ArrayList<>();
        Deque<Integer> stk = new ArrayDeque<>();
        
        for(int i = 0; i < progresses.length; i++) {
            // 작업일 계산하기
            int day = (100 - progresses[i]) / speeds[i];
            if((100 - progresses[i]) % speeds[i] != 0) {    // 올림(예: 95%, 개발 속도 4% = 2일)
                day++;
            }
            
            // 작업일 기반으로 배포 개수 구하기
            if(stk.isEmpty() || stk.peek() < day) {
                stk.push(day);
                answer.add(1);
            } else {
                int idx = answer.size() - 1;
                answer.set(idx, answer.get(idx) + 1);
            }
            
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}