import java.util.*;
import java.io.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        
        List<List<int[]>> graph = new ArrayList<>();
        
        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] edge : road) {
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];
            
            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }
        
        return dijkstra(graph, N, K);
    }
    
    int dijkstra(List<List<int[]>> graph, int N, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> a[1] - b[1]
        );  // {다음 정점, 누적 거리}
        int[] dist = new int[N + 1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        pq.offer(new int[]{1, 0});
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];
            
            if(cost > dist[node]) {
                continue;
            }
            
            for(int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextCost = cost + next[1];  // 사실상 그래프의 next[1]은 A-B 사이 거리(가중치) 이므로 누적 거리 + 가중치
                
                if(nextCost < dist[nextNode]) {
                    pq.offer(new int[]{nextNode, nextCost});
                    dist[nextNode] = nextCost;
                }
            }
        }
        
        int result = 0;
        
        for(int i = 1; i <= N; i++) {
            if(dist[i] <= K) {
                result++;
            }
        }
        
        return result;
    }
}