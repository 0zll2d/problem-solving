class Solution {
    public int solution(int n, int w, int num) {

        int h = n % w == 0 ? n / w : n / w + 1; // 전체 층 수

        int[][] boxes = new int[h][w];
        int row = 0;
        int col = 0;

        for(int i = 0, b = 1; i < h; i++) {
            if(i % 2 == 0) {
                for(int j = 0; j < w && b <= n; j++) {
                    if(b == num) {
                        row = i;
                        col = j;
                    }
                    boxes[i][j] = b++;
                }
            } else {
                for(int j = w - 1; j >= 0 && b <= n; j--) {
                    if(b == num) {
                        row = i;
                        col = j;
                    }
                    boxes[i][j] = b++;
                }
            }
        }

        int answer = h - row;

        if(boxes[h - 1][col] == 0) {
            answer--;
        }

        return answer;
    }
}