import java.util.*;

class Solution {
    Set<Integer> st = new HashSet<>();

    public int solution(String numbers) {

        makeNumber(numbers, new boolean[numbers.length()], "");

        return st.size();
    }

    void makeNumber(String numbers, boolean[] visited, String snum) {
        if(!snum.isEmpty()) {
            int num = Integer.parseInt(snum);
            if(isPrime(num)) {
                st.add(num);
            }
        }

        for(int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                makeNumber(numbers, visited, snum + numbers.charAt(i));
                visited[i] = false;
            }
        }
    }

    boolean isPrime(int num) {
        if(num <= 1) {
            return false;
        }

        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) {
                return false;
            }
        }
        return true;
    }
}