import java.util.*;

class Solution {
    
    int n, m;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    boolean[][] visited;
    int[] islands;
    
    public int[] solution(String[] maps) {
        
        n = maps.length;
        m = maps[0].length();
        
        visited = new boolean[n][m];
        islands = new int[10001];
        
        int island = 1;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maps[i].charAt(j) != 'X' && !visited[i][j]) {
                    dfs(maps, i, j, island);
                    island++;
                }
            }
        }
        
        if (island == 1) {
            return new int[]{-1};
        }
        
        List<Integer> answer = new ArrayList<>();
        for (int i = 1; i < island; i++) {
            answer.add(islands[i]);
        }
        
        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }
    
    void dfs(String[] maps, int x, int y, int num) {
        visited[x][y] = true;
        islands[num] += maps[x].charAt(y) - '0';
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= m || maps[nx].charAt(ny) == 'X' || visited[nx][ny]) {
                continue;
            }
            
            dfs(maps, nx, ny, num);
        }
    }
}
