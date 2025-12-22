class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 1; i * i <= n; i++) {   // i * i까지 순회
            if(n % i == 0) {
                int j = n / i;
                answer += i + (i == j ? 0 : j);
            }
        }
        
        return answer;
    }
}