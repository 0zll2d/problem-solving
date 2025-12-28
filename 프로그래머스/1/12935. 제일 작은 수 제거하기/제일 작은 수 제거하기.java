import java.util.*;

class Solution {
    public int[] solution(int[] arr) {        
        if(arr.length == 1) {   // 배열의 길이가 1이라면, 그 수가 제일 작은 수
            return new int[]{-1};
        }
        
        List<Integer> answer = new ArrayList<>();
        int min = arr[0];
        
        for(int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != min) {
                answer.add(arr[i]);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}