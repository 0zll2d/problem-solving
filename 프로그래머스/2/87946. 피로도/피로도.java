class Solution {
    int answer;
    boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        
        answer = 0;
        
        // 백트래킹을 하니 기존 for문이 필요 없어 제거
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        
        return answer;
    }
    
    void dfs(int k, int[][] dungeons, int cnt) {
        answer = Math.max(answer, cnt);
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!visited[i] && k >= dungeons[i][0]) {
                visited[i] = true;
                dfs(k - dungeons[i][1], dungeons, cnt + 1);
                visited[i] = false;
            }
        }
    }
}