class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;

        int n = schedules.length;   // 직원 수
        int[] lasttimes = new int[n];   // 출근 희망 시각 + 10분
        boolean[] isWeekend = new boolean[7];   // 주말 여부

        for(int i = 0; i < 7; i++) {
            if((startday + i) % 7 == 6 || (startday + i) % 7 == 0) {
                isWeekend[i] = true;
            }
        }

        for(int i = 0; i < n; i++) {
            int hour = schedules[i] / 100;
            int min = schedules[i] % 100 + 10;

            if(min >= 60) {
                hour += 1;
                min -= 60;
            }

            lasttimes[i] = hour * 100 + min;
        }

        for(int i = 0; i < n; i++) {
            boolean isWell = true;
            for(int j = 0; j < 7; j++) {
                if(!isWeekend[j] && timelogs[i][j] > lasttimes[i]) {
                    isWell = false;
                    break;
                }
            }
            if(isWell) {
                answer++;
            }
        }

        return answer;
    }
}