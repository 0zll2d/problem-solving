class Solution {
    int gcd(int a, int b) {
        if(b == 0) {
           return a; 
        }
        
        return gcd(b, a % b);
    }
    
    public int solution(int[] arr) {
        
        if(arr.length == 1) {
            return arr[0];
        }
        
        int answer = arr[0] * arr[1] / gcd(arr[0], arr[1]); // lcm
        
        for(int i = 2; i < arr.length; i++) {
            answer = answer * arr[i] / gcd(answer, arr[i]);
        }
        
        return answer;
    }
}