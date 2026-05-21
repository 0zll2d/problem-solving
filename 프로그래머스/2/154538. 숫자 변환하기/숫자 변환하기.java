import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        
        // [예외] x와 y가 같으면 변환 횟수 0
        if(x == y) {
            return 0;
        }
        
        int[] dp = new int[y + 1];
        
        Arrays.fill(dp, y - x);
        dp[y] = 0;
        
        for(int num = y; num > x; num--) {
            if(num / 2 >= x && num % 2 == 0) {
                dp[num / 2] = Math.min(dp[num / 2], dp[num] + 1);
            }
            if(num / 3 >= x && num % 3 == 0) {
                 dp[num / 3] = Math.min(dp[num / 3], dp[num] + 1);
            }
            if(num - n >= x) {
                 dp[num - n] = Math.min(dp[num - n], dp[num] + 1);
            }
        }
        
        return dp[x] == y - x ? -1 : dp[x];
    }
}