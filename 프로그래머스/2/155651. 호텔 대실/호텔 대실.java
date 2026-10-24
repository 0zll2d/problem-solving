import java.util.*;
import java.io.*;

class Solution {
    
    public int solution(String[][] book_time) {
        
        Arrays.sort(book_time, (b1, b2) -> b1[0].compareTo(b2[0])); // 입실 시각 순으로 정렬
        
        PriorityQueue<String[]> pq = new PriorityQueue<>((a, b) -> a[1].compareTo(b[1]));
        int n = book_time.length;
        
        for(String[] bk : book_time) {
            if(!pq.isEmpty()) {
                int out = convertTime(pq.peek()[1]);    // 객실 퇴실 시각
                int in = convertTime(bk[0]);
                
                // 다음 입실 시각이 퇴실 후 10분간 청소 후보다 뒤면 해당 객실 사용
                if(out + 10 <= in) {
                    pq.poll();
                }
            }
            pq.offer(bk);
        }
        
        return pq.size();
    }

    // 시각을 분으로 변환
    int convertTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        
        return hour * 60 + min;
    }
}