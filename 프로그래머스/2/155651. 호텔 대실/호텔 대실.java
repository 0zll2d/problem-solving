import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] book_time) {
        
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0])); // 입실 시각 기준으로 정렬
        
        // 객실의 개수 - 파라메트릭 서치, 이분 탐색 Lower Bound 해결
        int left = 1;
        int right = book_time.length;
        
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            if(canAllocate(book_time, mid)) {
                right = mid - 1;    // mid 객실 수로 이용 가능하다면 더 작은 객실 수로 가능한지 확인하기
            } else {
                left = mid + 1;     // mid 객실 수로 이용 불가능하다면 객실 수 늘리기
            }
        }
        
        return left;
    }
    
    boolean canAllocate(String[][] book_time, int room) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 방 퇴실시각
        
        for(String[] bk : book_time) {
            int in = convertTime(bk[0]);
            int out = convertTime(bk[1]);
            
            if(!pq.isEmpty() && pq.peek() + 10 <= in) {
                pq.poll();
            } 
            
            pq.offer(out);
            
            if(pq.size() > room) {
                return false;
            }
        }
        
        return true;
    }
    
    // 시각을 분으로 변환
    int convertTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        
        return hour * 60 + min;
    }
}