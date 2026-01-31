import java.util.*;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> m = new HashMap<>();
        m.put("code", 0);
        m.put("date", 1);
        m.put("maximum", 2);
        m.put("remain", 3);
        
        int idx_ext = m.get(ext);
        int idx_sort_by = m.get(sort_by);
        
        List<int[]> list = new ArrayList<>();
        
        for(int[] d : data) {
            if(d[idx_ext] < val_ext) {
                list.add(d);
            }
        }
        
        Collections.sort(list, (d1, d2) -> Integer.compare(d1[idx_sort_by], d2[idx_sort_by]));
        
        int[][] answer = new int[list.size()][4];
        
        for(int i = 0; i < list.size(); i++) {
           answer[i] = list.get(i);
        }
        
        return answer;
    }
}