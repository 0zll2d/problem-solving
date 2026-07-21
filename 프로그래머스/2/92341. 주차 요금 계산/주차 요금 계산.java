import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        List<Integer> answer = new ArrayList<>();
        
        // 1. 차랑별 누적 시간 계산
        Map<String, Integer> in = new HashMap<>();
        Map<String, Integer> parking = new TreeMap<>();  // 차량별 누적 주차 시간(차량 번호순 - TreeMap)
        
        for(String record : records) {
            String[] s = record.split(" ");
            String car = s[1];
            int time = calculateTime(s[0]);
            
            if("IN".equals(s[2])) {
                in.put(car, time);
            } else {    // "OUT"
                parking.put(car, parking.getOrDefault(car, 0) + time - in.get(car)); 
                in.remove(car);
            }
        }
        
        // 입차 이후 출차된 내역이 없으면 23:59에 출차된 것으로 간주
        for(String car : in.keySet()) {
            int time = calculateTime("23:59");
            parking.put(car, parking.getOrDefault(car, 0) + time - in.get(car));
        }
        
        // 2. 차량별 누적 시간 기반으로 요금 청구
        for(String car : parking.keySet()) {
            int time = parking.get(car);
            int fee = fees[1];  // 기본 시간 이하라면, 기본 요금 청구(항상 기본 요금을 기본으로 청구함)
            
            // 2. 기본 시간 초과 시, 초과한 시간에 대해 단위 시간 마다 단위 요금 청구
            if(time > fees[0]) {
                int unit = (time - fees[0]) / fees[2];
                if((time - fees[0]) % fees[2] != 0) {   // 올림
                    unit ++;
                }
                fee += unit * fees[3];
            }
            
            answer.add(fee);    
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
    
    int calculateTime(String stime) {
        int hour = Integer.parseInt(stime.substring(0, 2));
        int min = Integer.parseInt(stime.substring(3, 5));
        
        return hour * 60 + min;
    }
}
