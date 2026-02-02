import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        int size = board.length;

        ArrayDeque<Integer>[] stacks = new ArrayDeque[size];
        for(int i = 0; i < size; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        for(int j = 0; j < size; j++) {
            for(int i = size - 1; i >= 0; i--) {
                if(board[i][j] == 0) {
                    continue;
                }
                stacks[j].push(board[i][j]);
            }
        }

        ArrayDeque<Integer> basket = new ArrayDeque<>();
        for(int move : moves) {
            int idx = move -1;

            if(stacks[idx].isEmpty()) {
                continue;
            }

            int doll = stacks[idx].pop();

            if(!basket.isEmpty() && basket.peek() == doll) {
                basket.pop();
                answer += 2;    // 같은 모양 인형 2개가 만나면 터지므로 2개 더하기
            } else {
                basket.push(doll);
            }
        }

        return answer;
    }
}