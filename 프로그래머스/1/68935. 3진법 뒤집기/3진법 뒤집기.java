class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 가장 큰 3의 거듭제곱 찾기
        int weight = 1;
        
        while(weight * 3 <= n) {
            weight *= 3;
        }
        
        // 3진수 변환 (정방향)
        StringBuilder sb = new StringBuilder();
        
        while(weight > 0) {
            sb.append(n / weight);
            n %= weight;
            weight /= 3;
        }
        
        String ternary = sb.toString();
        int pow = 1;
        
        for(int i = 0; i < ternary.length(); i++) {
            answer += (ternary.charAt(i) - '0') * pow;
            pow *= 3;
        }
        
        return answer;
    }
}

// [ Java에서 거듭제곱 만드는 방법 ]
// Math.pow(double base, int exp);
// 반환형 double이기 때문에 부동소수점 오차가 생길 수 있어 주의