import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[] elements) {
        
        int n = elements.length;
        int[] sum = new int[n + 1];   // 누적합
        
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + elements[i - 1];
        }
        
        Set<Integer> st = new HashSet<>();  // 부분 수열의 합
        
        st.add(sum[n]); // elements 그 자체도 부분 수열(누적합)
        
        for(int len = 1; len < n; len++) {  // 부분 수열 길이 별 합 구하기
            for(int start = 0; start < n; start++) {    // 시작 인덱스
                int end = start + len;  // [start, end)
                
                if(end > n) {
                    end %= n;
                }
                
                int subSum = 0;
                
                if(start < end) {
                    subSum = sum[end] - sum[start];
                } else  {
                    subSum = sum[n] - sum[start] + sum[end];
                }
                
                st.add(subSum);
            }
        }
        
        return st.size();
    }
}