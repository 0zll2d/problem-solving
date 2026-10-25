import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        
        int maxValue = 0;
        
        for(int time : times) {
            maxValue = Math.max(maxValue, time);
        }
        
        long left = 1;
        long right = (long) maxValue * n;   // 오버플로우 주의
        
        while(left <= right) {
            long mid = left + (right - left) / 2;
            
            if(isPossible(n, times, mid)) {
                right = mid - 1;    // 해당 시간 안에 가능하면 더 땡겨보기
            } else {
                left = mid + 1;     // 해당 시간 안에 불가능하면 더 늘려보기
            }
        }
        
        return left;
    }
    
    // total분 안에 n명을 다 처리할 수 있는가?
    boolean isPossible(int n, int[] times, long total) {
        long sum = 0;   // 심사관 별 처리 가능한 인원 수의 합
        
        for(int time : times) {
            sum += total / time;
            
            if(sum >= n) {  // 처리할 수 있는 인원이 n명 보다 많이 처리할 수 있으면 early return
                return true;
            }
        }
        
        return false;
    }
}