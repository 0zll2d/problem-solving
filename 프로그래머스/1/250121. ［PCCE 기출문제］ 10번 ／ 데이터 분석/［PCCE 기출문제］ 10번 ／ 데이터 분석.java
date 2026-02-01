import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        List<String> list = List.of("code", "date", "maximum", "remain");
        int idx_ext = list.indexOf(ext);
        int idx_sort_by = list.indexOf(sort_by);
        
        int[][] answer = Arrays.stream(data).filter(d -> d[idx_ext] < val_ext).sorted((d1, d2) -> d1[idx_sort_by] - d2[idx_sort_by]).toArray(int[][]::new);
        
        return answer;
    }
}