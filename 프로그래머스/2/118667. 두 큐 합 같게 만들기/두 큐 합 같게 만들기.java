import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Deque<Integer> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();

        long sum1 = 0;
        long sum2 = 0;

        for(int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            sum1 += queue1[i];
        }

        for(int i = 0; i < queue2.length; i++) {
            q2.offer(queue2[i]);
            sum2 += queue2[i];
        }
        
        int limit = (queue1.length + queue2.length) * 2;    // 종료 조건 필요(없으면 시간초과)

        while(!q1.isEmpty() && !q2.isEmpty() && answer <= limit) {
            int p1 = q1.peek();
            int p2 = q2.peek();

            if(sum1 > sum2) {
                q1.poll();
                sum1 -= p1;
                q2.offer(p1);
                sum2 += p1;
            } else if(sum1 < sum2) {
                q1.offer(p2);
                sum1 += p2;
                q2.poll();
                sum2 -= p2;
            } else {
                break;
            }

            answer++;
        }

        return sum1 == sum2 ? answer : -1;
    }
}