import java.util.*;

class Solution {
    public String solution(String s) {
        int[] nums = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
    
        Arrays.sort(nums);
        
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append(" ").append(nums[nums.length - 1]);
        
        return sb.toString();
    }
}