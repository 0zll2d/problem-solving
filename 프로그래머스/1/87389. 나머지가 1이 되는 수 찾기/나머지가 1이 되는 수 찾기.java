import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 1;
        int target = n - 1;
        boolean[] isPrime = new boolean[n];
        
        // 1. 기본값 true로 설정
        Arrays.fill(isPrime, true);
        
        // 2. 에라토스테네스의 체
        for(int i = 2; i * i <= target; i++) {
            if(isPrime[i]) {
                for(int j = i * i; j <= target; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // 3. x값 찾기
        for(int i = 2; i <= target; i++) {
            if(isPrime[i] && target % i == 0) {
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}

// 핵심 아이디어
// n % x == 1  ⇔  (n - 1) % x == 0
// 즉, x는 (n - 1)의 약수이다.
// 가장 작은 x를 찾아야 하므로 작은 수부터 검사한다.
// * 소수가 아닌 수는 "어떤 소수"의 배수이므로, 가장 작은 x는 결국 소수이다.
//
// 에라토스테네스의 체를 이용해
// (n - 1) 이하의 소수를 미리 구한 뒤,
// 소수이면서 (n - 1)을 나누는 첫 번째 값을 정답으로 선택한다.

// 핵심 아이디어: 소수가 아닌 수는 "어떤 소수"의 배수이다. 결국 x는 소수이다.

// [ 에라토스테네스의 체에서 √x 까지만 확인해도 되는 이유 ]
//
// 어떤 수 x가 합성수라면
// x = a × b 형태로 표현할 수 있다.
//
// 이때 a ≤ b 라고 가정하면,
// a는 항상 √x 이하가 된다.
//
// 예: x = 36
// 1 × 36
// 2 × 18
// 3 × 12
// 4 × 9
// 6 × 6  ← √36 = 6
//
// 따라서 √x를 초과하는 수로 시작하는 배수는
// 이미 이전 단계에서 제거되었으므로
// 체에서는 √x까지만 확인하면 충분하다.
//
// ※ √x를 포함해서 검사해야 하는 이유는
// x가 완전제곱수일 경우 (예: 25 = 5 × 5)
// 제곱근 자체도 약수가 되기 때문이다.

// [ 에라토스테네스의 체 시간 복잡도 ]
// 소수의 배수만 제거하므로
// 전체 시간 복잡도는 O(N log log N) 이다.
// (log log N은 매우 작아서 실전에서는 거의 O(N)에 가깝다)

