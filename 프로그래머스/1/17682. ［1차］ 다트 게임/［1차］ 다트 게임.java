class Solution {
    public int solution(String dartResult) {
        int answer = 0;

        int[] result = new int[3];
        char[] arr = dartResult.toCharArray();
        int idx = 0;

        for(int game = 0; game < 3 && idx < arr.length; game++) {
            // 점수
            int score = arr[idx++] - '0';
            // 점수가 10점인 경우
            if(Character.isDigit(arr[idx])) {
                score = score * 10 + arr[idx++] - '0';
            }

            // 보너스
            char bonus = arr[idx++];
            if(bonus == 'D') {
                score *= score;
            } else if(bonus == 'T') {
                score *= score * score;
            }

            // 옵션
            if(idx < arr.length) {
                char option = arr[idx];

                if(option == '*') {
                    if(game > 0) {
                        result[game - 1] *= 2;
                    }
                    score *= 2;
                    idx++;
                } else if(option == '#') {
                    score *= -1;
                    idx++;
                }
            }

            // 점수 계산
            result[game] = score;
        }

        for(int i = 0; i < 3; i++) {
            answer += result[i];
        }

        return answer;
    }
}