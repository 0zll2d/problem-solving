import java.util.*;

class Solution {
    public int[] solution(String[] park, String[] routes) {

        int H = park.length;
        int W = routes.length;

        // 시작 지점 좌표
        int sx = 0;
        int sy = 0;

        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(park[i].charAt(j) == 'S') {
                    sx = i;
                    sy = j;
                    break;
                }
            }
        }

        // 방향 맵
        Map<String, int[]> dm = new HashMap<>();
        dm.put("N", new int[]{-1, 0});
        dm.put("S", new int[]{1, 0});
        dm.put("W", new int[]{0, -1});
        dm.put("E", new int[]{0, 1});

        for(int i = 0; i < routes.length; i++) {
            String[] route = routes[i].split(" ");
            String op = route[0];   // 이동할 방향
            int n = Integer.parseInt(route[1]); // 이동할 칸의 수

            // 이동 가능 여부
            boolean move = true;

            for(int d = 1; d <= n; d++) {
                // 이동 좌표
                int nx = sx + dm.get(op)[0] * d;
                int ny = sy + dm.get(op)[1] * d;

                // 공원을 벗어나는지 혹은 장애물을 만나는지 확인
                if(isOutOfBounds(H, W, nx, ny) || park[nx].charAt(ny) == 'X') {
                    move = false;
                    break;
                }
            }

            if(move) {
                sx += dm.get(op)[0] * n;
                sy += dm.get(op)[1] * n;
            }

        }

        return new int[]{sx, sy};
    }

    private boolean isOutOfBounds(int H, int W, int x, int y) {
        return x < 0 || x >= H || y < 0 || y >= W;
    }
}