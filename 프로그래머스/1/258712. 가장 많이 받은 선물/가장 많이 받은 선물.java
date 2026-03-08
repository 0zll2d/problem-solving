import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;

        int N = friends.length;

        // 1. 친구의 이름과 인덱스 매핑
        Map<String, Integer> m = new HashMap<>();

        for(int i = 0; i < N; i++) {
            m.put(friends[i], i);
        }

        // 2. 이번 달 주고 받은 선물 구하기
        int[][] giveAndTake = new int[N][N];    // 주고 받은 선물
        int[][] giftIndex = new int[N][3];      // 선물 지수

        for(String gift : gifts) {
            String[] s = gift.split(" ");
            int give = m.get(s[0]);
            int take = m.get(s[1]);

            giveAndTake[give][take]++;
            giftIndex[give][0]++;
            giftIndex[take][1]++;
        }

        // 3. 주고 받은 선물로 선물지수 계산하기
        for(int i = 0; i < N; i++) {
            giftIndex[i][2] = giftIndex[i][0] - giftIndex[i][1];
        }

        // 4. 선물지수 기반으로 다음 달 받을 선물의 개수 계산하기
        for(int i = 0; i < N; i++) {
            int next = 0;   // 다음 달에 받을 선물의 개수

            for(int j = 0; j < N; j++) {
                if(i == j) {
                    continue;
                }
                if(giveAndTake[i][j] == giveAndTake[j][i]) { // 두 사람이 선물을 주고 받은 기록이 하나도 없거나 주고받은 수가 같다면
                    if(giftIndex[i][2] > giftIndex[j][2]) {
                        next++;
                    }
                } else if(giveAndTake[i][j] > 0 || giveAndTake[j][i] > 0) { // 두 사람이 선물을 주고 받았다면
                    if(giveAndTake[i][j] > giveAndTake[j][i]) {
                        next++;
                    }
                }
            }

            answer = Math.max(answer, next);
        }

        return answer;
    }
}