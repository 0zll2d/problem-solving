import java.util.*;

class Solution {

    // graph에서는 간선 정보, PriorityQueue에서는 현재까지의 누적 비용을 저장하는 데 사용
    static class Node {
        int vertex;
        int weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public int solution(int N, int[][] road, int K) {

        // 1. 그래프 생성
        List<Node>[] graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] r : road) {
            int from = r[0];
            int to = r[1];
            int weight = r[2];

            // 양방향 그래프
            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        // 2. 다익스트라
        return dijkstra(N, K, graph);
    }

    int dijkstra(int N, int K, List<Node>[] graph) {

        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) -> a.weight - b.weight);

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[1] = 0;
        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {

            Node cur = pq.poll();

            int now = cur.vertex;
            int cost = cur.weight;   // 시작점 -> now까지의 누적 비용

            // 이미 더 짧은 경로를 알고 있다면 무시
            if (cost > dist[now]) {
                continue;
            }

            // 현재 정점과 연결된 간선 탐색
            for (Node edge : graph[now]) {

                int next = edge.vertex;
                int newCost = dist[now] + edge.weight;

                if (dist[next] > newCost) {

                    dist[next] = newCost;

                    // PQ에는 "누적 비용"을 넣는다.
                    pq.offer(new Node(next, newCost));
                }
            }
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                result++;
            }
        }

        return result;
    }
}