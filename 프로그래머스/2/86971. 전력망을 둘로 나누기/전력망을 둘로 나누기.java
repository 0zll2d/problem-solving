import java.util.*;

class Solution {
    int dfs(int[] wire, List<List<Integer>> graph, boolean[] visited, int cur) {
        visited[cur] = true;
        int ret = 1;

        for(int next : graph.get(cur)) {
            if(visited[next] || (cur == wire[0] && next == wire[1] || cur == wire[1] && next == wire[0])) {
                continue;
            }
           ret += dfs(wire, graph, visited, next);
        }

        return ret;
    }

    public int solution(int n, int[][] wires) {
        int answer = n;

        // 1. 그래프 만들기
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] wire : wires) {
            graph.get(wire[0]).add(wire[1]);
            graph.get(wire[1]).add(wire[0]);
        }

        // 2. 전력망을 2개로 나눴을 때 개수 구하기
        for(int[] wire : wires) {
            int depth1 = dfs(wire, graph, new boolean[n + 1], wire[0]);
            int depth2 = dfs(wire, graph, new boolean[n + 1], wire[1]);

            answer = Math.min(answer, Math.abs(depth1 - depth2));
        }

        return answer;
    }
}