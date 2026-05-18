import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        Deque<int[]> q = new ArrayDeque<>();    // {location, priority}
        PriorityQueue<Integer> pq = new PriorityQueue<>((p1, p2) -> p2 - p1); // 기본 최소힙을 최대힙으로 변환
        
        for(int i = 0; i < priorities.length; i++) {
            q.offer(new int[]{i, priorities[i]});
            pq.offer(priorities[i]);
        }
        
        while(!pq.isEmpty()) {
            int p = pq.poll();
            // 1. 실행 대기 큐에서 대기중인 프로세스 꺼내기
            int[] prcs = q.poll();
            
            // 2. 큐에 대기중인 프로세스 중 우선 순위가 더 높은 프로세스가 있다면 다시 큐에 넣기
            while(p != prcs[1]) {
                q.offer(prcs);
                prcs = q.poll();
            }
            
            // 3. 우선순위가 가장 높은 프로세스 실행
            answer++;
            
            if(prcs[0] == location) {
                break;
            }
        }
        
        return answer;
    }
}