import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[][] book_time) {
        
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));
            
        List<String[]> rooms = new ArrayList<>();
            
        for(String[] bk : book_time) {
            boolean isNew = true;  // 새 객실 필요
                
            for(int i = 0; i < rooms.size(); i++) {
                int out = convertTime(rooms.get(i)[1]); // 퇴실 시각
                int in = convertTime(bk[0]); // 다음 입실 시각
                    
                if(out + 10 <= in) {
                    rooms.set(i, bk);
                    isNew = false;
                    break;
                }
            }
             
            if(isNew) {
                rooms.add(bk);
            }
        }
        
        return rooms.size();
    }
    
    // 시각을 분으로 변환
    int convertTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        
        return hour * 60 + min;
    }
}