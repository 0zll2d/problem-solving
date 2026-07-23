import java.util.*;

class Solution {
    
    int INF = Integer.MAX_VALUE;
    
    static class Node {
        int idx;
        int cost;   // 누적 비용
        
        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        
        int answer = INF;
        
        // 1. 그래프 만들기
        List<Node>[] graph = new ArrayList[n + 1];
        int[][] dist = new int[n + 1][n + 1];
        
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }
        
        for(int[] fare: fares) {
            int c = fare[0];
            int d = fare[1];
            int f = fare[2];
            
            // 양방향
            graph[c].add(new Node(d, f));
            graph[d].add(new Node(c, f));
        }
        
        // 2. [다익스트라] 최단 거리 구하기
        //      (1) S 정점에서 다른 정점 최단 거리 구하기
        dijkstra(n, graph, dist, s);
        //      (2) A 정점에서 다른 정점 최단 거리 구하기
        dijkstra(n, graph, dist, a);
        //      (3) B 정점에서 다른 정점 최단 거리 구하기
        dijkstra(n, graph, dist, b);
        
        for(int i = 1; i <= n; i++) {
            if(dist[s][i] == INF || dist[a][i] == INF || dist[b][i] == INF) {
                continue;
            }
            
            answer = Math.min(answer, dist[s][i] + dist[a][i] + dist[b][i]);
        }
    
        return answer;
    }
    
    void dijkstra(int n, List<Node>[] graph, int[][] dist, int idx) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
        
        pq.offer(new Node(idx, 0));
        dist[idx][idx] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.idx;
            int cost = cur.cost;
            
            if(dist[idx][now] < cost) {
                continue;
            }
            
            for(Node edge : graph[now]) {
                int next = edge.idx;
                int newCost = dist[idx][now] + edge.cost;
                
                if(dist[idx][next] > newCost) {
                    pq.offer(new Node(next, newCost));
                    dist[idx][next] = newCost;
                }
            }
        }
    }
}