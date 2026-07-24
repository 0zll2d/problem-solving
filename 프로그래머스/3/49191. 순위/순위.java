import java.util.*;

class Solution {
    
    public int solution(int n, int[][] results) {
        
        // 1. 그래프 그리기
        List<Integer>[] winGraph = new ArrayList[n + 1];
        List<Integer>[] loseGraph = new ArrayList[n + 1];
        
        for(int i = 0; i <= n; i++) {
            winGraph[i] = new ArrayList<>();
            loseGraph[i] = new ArrayList<>();
        }
        
        for(int[] result : results) {
            int A = result[0];
            int B = result[1];
            
            winGraph[A].add(B);
            loseGraph[B].add(A);
        }

        // 2. 본인(i)이 이긴 사람 수 + 진 사람 수  - 1(본인) = n
        int answer = 0;
        
        for(int i = 1; i <= n; i++) {
            int win = dfs(n, winGraph, new boolean[n + 1], i);
            int lose = dfs(n, loseGraph, new boolean[n + 1], i);
            
            if(win + lose - 1 == n) {   // 본인(i) 제외
                answer++;
            }
        }
        
        return answer;
    }
    
    int dfs(int n, List<Integer>[] graph, boolean[] visited, int idx) {
        int ret = 1;
        
        visited[idx] = true;
        
        for(int next : graph[idx]) {
            if(!visited[next]) {
                ret += dfs(n, graph, visited, next);
            }
        }
        
        return ret;
    }
    
}
