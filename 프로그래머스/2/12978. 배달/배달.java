import java.util.*;

class Solution {
    
    static class Node {
        int vertex;
        int weight;
        
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // 1. 그래프 만들기
        List<Node>[] graph = new ArrayList[N + 1];
        
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            
            // 양방향
            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }
        
        // 2. 다익스트라로 배달이 가능한 마을의 개수 구하기
        answer = dijkstra(N, K, graph);

        return answer;
    }
    
    int dijkstra(int N, int K, List<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.weight - b.weight));
        int[] dist = new int[N + 1];   // 1번 마을에서 N번 마을까지 최단거리
        int result = 0;
        
        for(int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        
        pq.offer(new Node(1, 0));
        dist[1] = 0;
        
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.vertex;
            int cost = cur.weight;
            
            // PQ에서 꺼낸 정보가 최단 경로가 아니면 무시한다.
            // <= 가 아닌 이유: 동일한 정점, 하지만 다른 비용을 가진 노드가 PQ에 저장될 수 있다. (최단 경로 파악 위해)
            if(dist[now] < cost) {
                continue;
            }
            
            for(Node n : graph[now]) {
                int next = n.vertex;
                int ncost = n.weight;
                
                if(dist[next] > dist[now] + ncost) {
                    pq.offer(new Node(next, ncost));
                    dist[next] = dist[now] + ncost;
                }
            }
        }
        
        for(int i = 1; i <= N; i++) {
            if(dist[i] <= K) {
                result++;
            }
        }
        
        return result;
    }
    
}