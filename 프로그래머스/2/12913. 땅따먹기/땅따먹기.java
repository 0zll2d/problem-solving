import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        
        int N = land.length;
        int[][] dp = new int[N][4];
        
        for(int j = 0; j < 4; j++) {
            dp[0][j] = land[0][j];
        }
        
        for(int i = 1; i < N; i++) {
            for(int j = 0; j < 4; j++) {        // 기준
                for(int k = 0; k < 4; k++) {    // 비교값
                    if(j == k) {
                        continue;
                    }
                    dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + land[i][k]);
                }
            }
        }
        
        for(int j = 0; j < 4; j++) {
            answer = Math.max(answer, dp[N - 1][j]);
        }

        return answer;
    }
}