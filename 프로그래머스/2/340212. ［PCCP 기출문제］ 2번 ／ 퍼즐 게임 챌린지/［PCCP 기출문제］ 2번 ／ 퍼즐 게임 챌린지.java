import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        
        // level 숙련도 파라메트릭 서치
        int left = 1;
        int right = 100000;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(isPossible(diffs, times, limit, mid)) {
                right = mid - 1;    // limit 시간 안에 해결 가능한 숙련도이면 최솟값 찾기
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    boolean isPossible(int[] diffs, int[] times, long limit, int level) {
        long total = times[0]; // 총 걸린 시간
        
        for(int i = 1; i < diffs.length; i++) {
            if(diffs[i] <= level) {
                total += times[i];
            } else {
                total += ((long) times[i] + times[i - 1]) * (diffs[i] - level) + times[i];  // 오버플로우 주의
            }
            
            if(total > limit) {
                return false;
            }
        }
        
        return true;
    }
}