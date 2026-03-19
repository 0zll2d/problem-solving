import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<String, String> info = new HashMap<>();   // { 차량 번호, 입차 시간 }
        Map<String, Integer> res = new TreeMap<>();   // { 차량 번호, 누적 시간 }   +   key 값으로 정렬되는 TreeMap 사용

        // 1. 입/출차 내역에서 차량 별 누적 주차 시간 구하기
        for(String record : records) {
            String[] r = record.split(" ");

            // 시각
            int hour = Integer.parseInt(r[0].substring(0, 2));
            int min = Integer.parseInt(r[0].substring(3, 5));

            String num = r[1];  // 차량 번호
            String cmd = r[2];  // 내역

            if("IN".equals(cmd)) {
                info.put(num, r[0]);
            } else {    // "OUT" 이면
                String time = info.get(num);
                int inHour = Integer.parseInt(time.substring(0, 2));
                int inMin = Integer.parseInt(time.substring(3, 5));

                int diff = hour * 60 + min - (inHour * 60 + inMin); // 누적 주차 시간 구하기

                info.remove(num);  // 맵에서 제거(또 입차할 가능성)
                res.put(num, res.getOrDefault(num, 0) + diff);    // 차별 누적 주차 시간 추가
            }
        }

        // 2. 입차된 이후 출차된 내역이 없는 차량의 누적 주차 시간 구하기
        for(String num : new ArrayList<>(info.keySet())) {  // 안전하게 key 복사해서 사용
            String time = info.get(num);
            int inHour = Integer.parseInt(time.substring(0, 2));
            int inMin = Integer.parseInt(time.substring(3, 5));

            int diff = 23 * 60 + 59 - (inHour * 60 + inMin); // 23:59 출차로 간주하고 계산

            info.remove(num);  // 맵에서 제거(또 입차할 가능성)
            res.put(num, res.getOrDefault(num, 0) + diff);    // 차별 누적 주차 시간 추가
        }

        // 3. 차별 주차요금 계산
        int[] answer = new int[res.size()];
        int idx = 0;

        for(String num : res.keySet()) {
            int time = res.get(num);
            int money = fees[1];    // 기본 요금

            if(time > fees[0]) {    // 누적 주차 시간이 기본 시간을 초과하면
                money += (time - fees[0] + fees[2] - 1) / fees[2] * fees[3];
            }

            answer[idx++] = money;
        }

        return answer;
    }
}