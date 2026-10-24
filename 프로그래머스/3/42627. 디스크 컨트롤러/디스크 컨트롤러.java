import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] jobs) {
        int n = jobs.length;
        
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]); // 요청 시각 기준으로 정렬

        // 대기큐 [s(요청 시각), l(소요 시간), i(작업 번호)]
        PriorityQueue<int[]> pq = new PriorityQueue<>((t1, t2) -> {
            if(t1[1] == t2[1]) {
                if(t1[0] == t2[0]) {
                    return t1[2] - t2[2];   // 3. 작업 번호가 작은 순
                }
                return t1[0] - t2[0];   // 2. 요청 시각이 빠른 순
            }
            return t1[1] - t2[1];  // 1. 소요 시간이 짧은 순
        });

        int idx = 0;
        int cnt = 0;    // 완료된 작업 수
        int end = 0;    // 작업 종료 시각
        int answer = 0;
        
        while(cnt < n) {
            // 요청 시각이 작업 종료 시각보다 빠르면 대기큐 추가
            while(idx < n && jobs[idx][0] <= end) {
                pq.offer(new int[]{jobs[idx][0], jobs[idx][1], idx});
                idx++;
            }
            
            if(pq.isEmpty()) {
                end = jobs[idx][0];
                continue;
            }
            
            int[] job = pq.poll();
            end += job[1];
            answer += end - job[0];
            cnt++;
        }
        
        return answer / n;
    }
}