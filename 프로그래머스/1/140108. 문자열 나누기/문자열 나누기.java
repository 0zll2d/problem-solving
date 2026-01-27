class Solution {
    public int solution(String s) {
        int answer = 0;

        char c = s.charAt(0);
        int x = 1;
        int notX = 0;

        for(int i = 1; i < s.length(); i++) {
            if(x == notX) {
                answer++;
                c = s.charAt(i);
                x = 0;
                notX = 0;
            }

            if(c == s.charAt(i)) {
                x++;
            } else {
                notX++;
            }
        }

        // 반복문 종료되고 마지막 남은 문자열
        answer++;

        return answer;
    }
}