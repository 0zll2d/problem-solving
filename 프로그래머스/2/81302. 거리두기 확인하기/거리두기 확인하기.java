import java.util.*;

class Solution {

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            char[][] place = new char[5][5];

            for (int j = 0; j < 5; j++) {
                place[j] = places[i][j].toCharArray();
            }

            // 1. 응시자 위치 찾기
            List<int[]> points = new ArrayList<>();

            for (int x = 0; x < 5; x++) {
                for (int y = 0; y < 5; y++) {
                    if (place[x][y] == 'P') {
                        points.add(new int[]{x, y});
                    }
                }
            }

            // 2. 응시자끼리 맨해튼 거리 확인
            boolean isValid = true;

            for (int a = 0; a < points.size() - 1; a++) {
                for (int b = a + 1; b < points.size(); b++) {
                    int[] p1 = points.get(a);
                    int[] p2 = points.get(b);

                    int dist = Math.abs(p1[0] - p2[0])
                            + Math.abs(p1[1] - p2[1]);

                    // 맨해튼 거리가 1이면 바로 거리두기 위반
                    if (dist == 1) {
                        isValid = false;
                        break;
                    }

                    // 맨해튼 거리가 2이면 파티션 확인
                    if (dist == 2 && !check(place, p1, p2)) {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    break;
                }
            }

            answer[i] = isValid ? 1 : 0;
        }

        return answer;
    }

    // 두 응시자 사이가 파티션으로 막혀 있는지 확인
    boolean check(char[][] board, int[] start, int[] target) {
        int x1 = start[0];
        int y1 = start[1];

        int x2 = target[0];
        int y2 = target[1];

        int diffX = Math.abs(x1 - x2);
        int diffY = Math.abs(y1 - y2);

        // 같은 열에서 세로로 두 칸 떨어진 경우
        if (diffX == 2 && diffY == 0) {
            int midX = (x1 + x2) / 2;

            return board[midX][y1] == 'X';
        }

        // 같은 행에서 가로로 두 칸 떨어진 경우
        if (diffX == 0 && diffY == 2) {
            int midY = (y1 + y2) / 2;

            return board[x1][midY] == 'X';
        }

        // 대각선으로 떨어진 경우
        if (diffX == 1 && diffY == 1) {
            return board[x1][y2] == 'X'
                    && board[x2][y1] == 'X';
        }

        return true;
    }
}