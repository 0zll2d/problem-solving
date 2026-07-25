class Solution {
    
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        int answer = 0; // 연결된 네트워크 수
                
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(n, computers, i);
                answer++;
            }
        }
        
        return answer;
    }
    
    void dfs(int n, int[][] computers, int idx) {
        visited[idx] = true;
        
        for(int i = 0; i < n; i++) {
            if(!visited[i] && computers[idx][i] == 1) {
                dfs(n, computers, i);
            }
        }
    }
}