import java.util.*;

class Solution {
    public int[] solution(int n) {

        int num = n * (n + 1) / 2;  // 마지막 숫자 (1 ~ n까지의 합)
        int[][] triangle = new int[n][n];

        int d = 1;
        int x = 0;
        int y = 0;

        while(d <= num) {
            // 1. 상 -> 하 방향
            while(d <= num && x < n && triangle[x][y] == 0) {
                triangle[x++][y] = d++;
            }
            x--; y++;

            // 2. 왼 -> 오
            while(d <= num && y < n && triangle[x][y] == 0) {
                triangle[x][y++] = d++;
            }
            x--; y -= 2;

            // 3. 대각선 방향
            while(d <= num && x >= 0 && y >= 0 && triangle[x][y] == 0) {
                triangle[x--][y--] = d++;
            }
            x += 2; y++;
        }

        List<Integer> answer = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i + 1; j++) {
                answer.add(triangle[i][j]);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}