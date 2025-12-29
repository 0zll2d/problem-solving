class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        
        for(int i = left; i <= right; i++) {
            // 약수의 개수가 홀수 = 완전 제곱수 (예: 25, 36)
            int sqrt = (int) Math.sqrt(i);
            
            if(sqrt * sqrt == i) {
                answer -= i;
            } else {
                answer += i;
            }
        }
        
        return answer;
    }
}

/**
 * [ i / sqrt == sqrt 가 적절하지 않은 이유 ]
 *
 * i / sqrt 는 정수 나눗셈으로 수행되기 때문에
 * 나눗셈 결과의 소수점 이하가 모두 버려진다.
 *
 * 이로 인해 완전제곱수 여부를 정확하게 판단할 수 없다.
 *
 * 예시)
 *  - i = 15
 *  - sqrt = (int) Math.sqrt(15) = 3
 *
 *  정수 나눗셈 결과:
 *  - i / sqrt = 15 / 3 = 5
 *
 * 따라서 완전제곱수 판별은
 *  - sqrt * sqrt == i
 * 와 같이 곱셈을 이용한 정수 비교 방식이 안전하고 권장된다.
 */
