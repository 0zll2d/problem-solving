import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    boolean isOutOfBounds(int n, int m, int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m);
    }

    // 풀이 1. BFS
    public int solution(int[][] maps) {
        int answer = -1;

        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];

        Deque<int[]> q = new ArrayDeque<>();    // { x, y }
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            if(x == n - 1 && y == m - 1) {
                answer = maps[x][y];
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isOutOfBounds(n, m, nx, ny) && maps[nx][ny] == 1 && !visited[nx][ny]) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                    maps[nx][ny] = maps[x][y] + 1;
                }
            }
        }

        return answer;
    }
}