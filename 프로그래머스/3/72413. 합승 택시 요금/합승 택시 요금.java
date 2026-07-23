import java.util.*;

class Solution {
    
    int INF = Integer.MAX_VALUE;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int answer = INF;
        
        int[][] dist = new int[n + 1][n + 1];
        
        for(int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for(int[] fare : fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            
            dist[c][d] = f;
            // 양방향 연결
            dist[d][c] = f;
        }
        
        // [플로이드 와샬] x ~ y까지 최단거리 = x ~ m까지 최단거리 + m ~ y까지 최단거리
        for(int m = 1; m <= n; m++) {
            for(int x = 1; x <= n; x++) {
                for(int y = 1; y <= n; y++) {
                    if(dist[x][m] == INF || dist[m][y] == INF) {
                        continue;
                    }
                    
                    if(dist[x][y] > dist[x][m] + dist[m][y]) {
                        dist[x][y] = dist[x][m] + dist[m][y];
                    }
                }
            }
        }
        
        for(int i = 1; i <= n; i++) {
            if(dist[s][i] != INF && dist[i][a] != INF && dist[i][b] != INF) {
                // A까지 택시 요금 + B까지 택시 요금
                int cost = dist[s][i] + dist[i][a] + dist[i][b];
                answer = Math.min(answer, cost);
            }
        }
        
        return answer;
    }
    
}