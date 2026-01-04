class Solution {
    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);
        int lcm = n / gcd * m;  // 곱셈을 나중에 수행하여 overflow 위험 감소
        
        return new int[] {gcd, lcm};
    }
    
    // 유클리드 호제법
    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    } 
}

/**
 * [유클리드 호제법 (Euclidean Algorithm)]
 * 두 정수의 최대공약수(GCD)를 구하는 알고리즘이다.
 *
 * 핵심 아이디어:
 * - 큰 수에서 작은 수를 여러 번 빼도 최대공약수는 변하지 않는다.
 * - 이 "여러 번 빼기"를 한 번에 수행한 결과가 나머지(mod)이다.
 *
 * 예시:
 * - 20과 12의 최대공약수를 구한다고 가정
 *   20 - 12 = 8
 *   → 20과 12의 최대공약수는
 *     12와 8의 최대공약수와 같다.
 *
 * 이를 반복하면:
 * - 12 - 8 = 4
 * - 8 - 4 = 0
 * → 나머지가 0이 되는 순간의 수(4)가 최대공약수
 *
 * 따라서,
 * gcd(a, b) = gcd(b, a % b)
 * 라는 형태로 재귀 또는 반복문으로 구현할 수 있다.
 *
 *
 * [최소공배수 (LCM: Least Common Multiple)]
 * 두 정수가 동시에 나누어떨어지는 가장 작은 수이다.
 *
 * 성질:
 * - 두 수 a, b에 대해
 *   a × b = GCD(a, b) × LCM(a, b)
 *
 * 따라서 최소공배수는 다음과 같이 구할 수 있다.
 * - LCM(a, b) = (a / GCD(a, b)) × b
 *
 * ※ a × b를 먼저 계산하지 않고
 *    a / GCD 후 곱셈을 하는 이유는
 *    정수 범위 초과(overflow)를 방지하기 위함이다.
 */



    
    
    
    
    
    
    
        
    
    
    
    
    
    
    