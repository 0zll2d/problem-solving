import java.util.*;

class Solution {
    int[] getTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        int total = hour * 60 + min;
        
        return new int[]{hour, min, total};
    }
    
    public int solution(String[][] book_time) {

        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0])); // 문자열 비교는 compareTo()
        
        List<int[]> rooms = new ArrayList<>();
        
        for(String[] bt : book_time) {
            int[] start_time = getTime(bt[0]);
            int[] end_time = getTime(bt[1]);
            
            // 모든 객실이 비어있으면
            if(rooms.isEmpty()) {
                rooms.add(end_time);
                continue;
            }
            
            // 모든 객실이 비어있지 않으면, 퇴실 시간 기준으로 사용할 수 있는 방 확인
            boolean is_new = true;  // 새 객실 필요
            
            for(int i = 0; i < rooms.size(); i++) {
                // 10분간 청소 후 객실 사용 가능
                if(rooms.get(i)[2] + 10 <= start_time[2]) {
                    rooms.set(i, end_time);
                    is_new = false;
                    break;
                }
            }
            
            if(is_new) {
                rooms.add(end_time);
            }
        }
        
        return rooms.size();
    }
}