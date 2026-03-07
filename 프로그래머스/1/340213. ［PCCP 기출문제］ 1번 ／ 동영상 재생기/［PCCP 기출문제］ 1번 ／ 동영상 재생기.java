class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        String start_after_ten = "00:10";
        String end_before_ten = calculateTime(video_len, false);
        
        // 1. 오프닝 구간 검사
        if(pos.compareTo(op_start) >= 0 && pos.compareTo(op_end) <= 0) {
            pos = op_end;
        }
        
        // 2. 사용자 입력
        for(String command : commands) {
            // 건너뛰기           
            if("prev".equals(command)) {
                // 현재 위치가 10초 미만이라면
                if(pos.compareTo(start_after_ten) < 0) {
                    pos = "00:00";
                } else {
                    pos = calculateTime(pos, false);
                }
            } else if("next".equals(command)) {
                // 현재 위치가 동영상의 남은 시간이 10초 미만인 경우
                if(pos.compareTo(end_before_ten) > 0) {
                    pos = video_len;
                } else {
                    pos = calculateTime(pos, true);
                }
            }
            
            // 오프닝 구간 검사
            if(pos.compareTo(op_start) >= 0 && pos.compareTo(op_end) <= 0) {
                pos = op_end;
            }
        }
                
        return pos;
    }
    
    private String calculateTime(String time, boolean plus_ten) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        
        if(plus_ten) {  // 10분 뒤로가기
            min += 10;
        } else {    // 10분 앞으로 가기
            min -= 10;
        }
        
        // 시간 보정
        if(min >= 60) {
            min -= 60;
            hour += 1;
        }
        
        if(min < 0) {
            min += 60;
            hour -= 1;
        }
        
        return String.format("%02d:%02d", hour, min);
    }
}