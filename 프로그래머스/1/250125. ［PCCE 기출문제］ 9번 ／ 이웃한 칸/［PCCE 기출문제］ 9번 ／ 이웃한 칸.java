class Solution {
    public int solution(String[][] board, int h, int w) {
        int answer = 0;

        String color = board[h][w];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int size = board.length;

        for(int i = 0; i < 4; i++) {
            int x = h + dx[i];
            int y = w + dy[i];

            if(x < 0 || x >= size || y < 0 || y >= size) {
                continue;
            }

            if(board[x][y].equals(color)) {
                answer++;
            }
        }

        return answer;
    }
}