import java.util.*;

class Solution {
    boolean isPrime(long num) {
        if(num < 2) return false;

        for(long i = 2; i * i <= num; i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int solution(int n, int k) {
        int answer = 0;

        // 정수 → 문자열, 진수 변환
        String snum = Integer.toString(n, k);

        // 구분자 '0'으로 변환
        String[] s = snum.split("0");

        for(int i = 0; i < s.length; i++) {
            if(s[i].isEmpty()) {    // "00" → "" 제외
                continue;
            }
            
            long num = Long.parseLong(s[i]);
            if(isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }
}