import java.util.*;
import java.io.*;

class Solution {
    
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        // 투포인터 풀이 [start, end)
        int start = 0;
        int end = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;
        
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
                int length = end - start;
                
                if(length < minLength || length == minLength && start < answer[0]) {
                    minLength = length;
                    answer = new int[]{start, end - 1};
                }
            }
        }
        
        return answer;
    }
}