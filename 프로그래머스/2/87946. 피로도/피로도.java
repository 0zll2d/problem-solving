class Solution {
    int answer;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        
        answer = 0;
        
        for(int i = 0; i < dungeons.length; i++) {
            visited = new boolean[dungeons.length];
            dfs(k, dungeons, visited, 0);
        }
        
        return answer;
    }
    
    void dfs(int k, int[][] dungeons, boolean[] visited, int cnt) {
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, visited, cnt + 1);
                visited[i] = false;
            }
        }
    }
}