import java.util.*;
import java.io.*;

class Solution {
    
    int n, m;
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[] isIncluded;
    int[] cols;

    public int solution(int[][] land) {

        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        cols = new int[m];

        for(int j = 0; j < m; j++) {
            for(int i = 0; i < n; i++) {
                if(land[i][j] == 1 && !visited[i][j]) {
                    isIncluded = new boolean[m];
                    bfs(land, i, j);
                }
            }
        }

        int answer = 0;

        for(int i = 0; i < m; i++) {
            answer = Math.max(answer, cols[i]);
        }

        return answer;
    }

    // 석유 덩어리(type)에 해당하는 col 리스트 반환
    void bfs(int[][] land, int sx, int sy) {

        Deque<int[]> q = new ArrayDeque<>();
        int result = 0; // 석유 덩어리 크기

        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            result++;
            if(!isIncluded[y]) {
                isIncluded[y] = true;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isOutOfBound(nx, ny) && land[nx][ny] == 1 && !visited[nx][ny]) {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            if(isIncluded[i]) {
                cols[i] += result;
            }
        }
    }

    boolean isOutOfBound(int x, int y) {
        return (x < 0|| x >= n || y  < 0 || y >= m);
    }
}