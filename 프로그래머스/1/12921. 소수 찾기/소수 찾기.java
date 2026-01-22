class Solution {
    public int solution(int n) {
        int answer = 0;

        for(int num = 2; num <= n; num++) {
            // 2이면 소수이므로 추가
            if(num == 2) {
                answer++;
                continue;
            }

            boolean isPrime = true;

            // 약수 찾기 알고리즘 기반으로 소수 판별
            for(int i = 2; i * i <= num; i++) {
                if(num % i == 0) {
                    isPrime = false;
                    break;
                }
            }

            if(isPrime) {
                answer++;
            }
        }

        return answer;
    }
}