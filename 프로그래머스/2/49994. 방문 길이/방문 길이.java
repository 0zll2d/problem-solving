import java.util.*;

class Solution {
    
    boolean isOutOfBounds(int x, int y) {
        return (x < 0 || x > 10 || y < 0 || y > 10);
    }
    
    public int solution(String dirs) {
        
        Map<Character, int[]> dm = new HashMap<>(); // directionMap
        dm.put('U', new int[]{0, 1});
        dm.put('D', new int[]{0, -1});
        dm.put('R', new int[]{1, 0});
        dm.put('L', new int[]{-1, 0});
        
        Map<String, Integer> rm = new HashMap<>();  // 처음 걸어본 길의 길이 (roadMap, 예: (0, 0) -> (0, 1) = "0011")
        int x = 5;  // 0, 0의 좌표
        int y = 5;
        
        for(int i = 0; i < dirs.length(); i++) {
            char dir = dirs.charAt(i);
            int nx = x + dm.get(dir)[0];
            int ny = y + dm.get(dir)[1];
            
            if(isOutOfBounds(nx, ny)) {
                continue;
            }
            
            // 정방향 (x, y -> nx, ny)
            String road1 = new StringBuilder()
                .append(x)
                .append(y)
                .append(nx)
                .append(ny)
                .toString();
            
            // 역방향 (nx, ny -> x, y)
            String road2 = new StringBuilder()
                .append(nx)
                .append(ny)
                .append(x)
                .append(y)
                .toString();
            
            // [중요] 정방향과 역방향은 모두 같은 길이다
            rm.put(road1, rm.getOrDefault(road1, 0) + 1);
            rm.put(road2, rm.getOrDefault(road2, 0) + 1);
            
            x = nx;
            y = ny;
        }

        return rm.size() / 2;
    }
}