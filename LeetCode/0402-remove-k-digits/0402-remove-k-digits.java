class Solution {
    public String removeKdigits(String num, int k) {
        
        Deque<Character> stk = new ArrayDeque<>();

        for(char c : num.toCharArray()) {
            while(!stk.isEmpty() && stk.peek() > c && k > 0) {
                stk.pop();
                k--;
            }

            stk.push(c);
        }

        // [Edge Case] k > 0 일 때(예: num = 12345), 이미 앞 자리수는 작은 수 이므로 남은 k개 만큼 제거
        while(k > 0) {
            stk.pop();
            k--;
        }       

        StringBuilder sb = new StringBuilder();

        while(!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        sb.reverse();   // 문자열 뒤집기

        // [Edge Case] leading zero 처리, num 길이가 10^5이므로 Integer 변환 할 수 없음(Long도 마찬가지)
        int idx = 0;

        while(sb.length() > 1 && idx < sb.length() && sb.charAt(idx) == '0') {
            idx++;
        }

        String answer = sb.substring(idx).toString();

        return answer.isEmpty() ? "0" : answer;
    }
}