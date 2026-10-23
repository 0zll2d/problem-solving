import java.util.*;
import java.io.*;

class Solution {
    
    int n, m;
    boolean[][] visited;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        visited = new boolean[n][m];
        
        List<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    answer.add(bfs(maps, i, j));
                }
            }
        }
        
        return answer.isEmpty() 
            ? new int[]{-1} 
            : answer.stream()
                .sorted()
                .mapToInt(i -> i)
                .toArray();
    }
    
    int bfs(String[] maps, int sx, int sy) {
        Deque<int[]> q = new ArrayDeque<>();
        int result = 0; // 섬에 머무를 수 있는 날
        
        q.offer(new int[]{sx, sy});
        visited[sx][sy] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int x = now[0];
            int y = now[1];
            
            result += maps[x].charAt(y) - '0';
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!isOutOfBounds(nx, ny) && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    q.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return result;
    }
    
    boolean isOutOfBounds(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m);
    }
}