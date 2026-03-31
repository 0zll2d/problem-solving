import java.util.*;

class Solution {
    int n;
    int m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    boolean isOutOfBounds(int x, int y) {
        return (x < 0 || x >= n || y < 0 || y >= m);
    }
    
    int bfs(String[] maps, int sx, int sy, char target) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];
            
            if(maps[x].charAt(y) == target) {
                return dist;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!isOutOfBounds(nx, ny) && maps[nx].charAt(ny) != 'X' && !visited[nx][ny]) {
                    q.offer(new int[]{nx, ny, dist + 1});
                    visited[nx][ny] = true;
                }
            }
        }
        
        return -1;
    }
    
    public int solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        
        int sx = 0;
        int sy = 0;
        int lx = 0;
        int ly = 0; 
        
        for(int i = 0; i < maps.length; i++) {
            int s = maps[i].indexOf('S');
            if(s != -1) {
                sx = i;
                sy = s;
            }
            
            int l = maps[i].indexOf('L');
            if(l != -1) {
                lx = i;
                ly = l;
            }
        }
        
        // 시작 지점부터 레버까지 최단거리
        int sToL = bfs(maps, sx, sy, 'L');
        
        // 레버부터 출구까지 최단거리
        int lToE = bfs(maps, lx, ly, 'E');
        
        return (sToL == -1 || lToE == -1 ? -1 : sToL + lToE);
    }
}