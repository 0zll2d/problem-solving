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
        List<Long> pList = new ArrayList<>();

        if(s.length == 0) { // P 조건 대응: 각 자릿수에 0을 포함하지 않는 소수
            pList.add(Long.parseLong(snum));
        } else {    // 0P0, 0P, P0 조건 대응
            for(int i = 0; i < s.length; i++) {
                if(s[i].isEmpty()) {    // "00" → "" 제외
                    continue;
                }
                pList.add(Long.parseLong(s[i]));
            }
        }
        // 따라서, { 0P, P0, 0P0 }와 { P }는 상반되는 조건

        for(Long num : pList) {
            if(isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }
}