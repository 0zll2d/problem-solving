import java.util.*;
import java.io.*;

class Solution {
    
    public int[] solution(int[] sequence, int k) {
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int alen = a[1] - a[0];
            int blen = b[1] - b[0];
            
            if(alen == blen) {
                return a[0] - b[0];
            }
            
            return alen - blen;
        });
        
        // 투포인터 풀이 [start, end)
        int start = 0;
        int end = 0;
        int sum = 0;
        
        while(start <= end) {
            if(sum < k && end < sequence.length) {
                sum += sequence[end];
                end++;
            } else {
                if(start == end) {  // 빈구간
                    break;
                }
                sum -= sequence[start];
                start++;
            }
            
            if(sum == k) {
                pq.offer(new int[]{start, end - 1});
            }
        }
        
        return pq.poll();
    }
}