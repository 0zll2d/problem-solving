import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();

        for(int num : nums){
            m.put(num, m.getOrDefault(num, 0) + 1 );
        }

        if(m.size() >= nums.length / 2) {
            return nums.length / 2;
        }

        return m.size();
    }
}