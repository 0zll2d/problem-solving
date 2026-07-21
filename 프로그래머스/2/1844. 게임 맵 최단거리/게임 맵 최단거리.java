import java.util.*;

class Solution {
    
    int n, m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length;
        
        answer = bfs(maps);
        
        return answer;
    }
    
    int bfs(int[][] maps) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        q.push(new int[]{0, 0, 1});
        visited[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if(x == n - 1 && y == m - 1) {
                return dist;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }
                
                q.offer(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }
        
        return -1;
    }
}