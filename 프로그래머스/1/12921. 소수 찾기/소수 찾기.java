import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;

        // 에라토스테네스의 체: 소수 판별 알고리즘이 필요 없음(배수를 제거하면 자연스럽게 남는 것이 소수이기 때문)
        // * 시간복잡도: O(n log log n)
        boolean[] isPrime = new boolean[n + 1];

        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for(int i = 2; i * i <= n; i++) {
            if(isPrime[i]) {
                for(int j = i * 2; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(isPrime[i]) {
                answer++;
            }
        }


        return answer;
    }
}