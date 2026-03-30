class Solution {
    public String solution(int n, int t, int m, int p) {
        String answer = "";
        
        int limit = m * t;
        String s = "0";
        int num = 1;
        
        while(s.length() <= limit) {
            s += Integer.toString(num++, n);
        }
        
        s = s.toUpperCase();    // 대문자 변환
        
        for(int i = 0; i < t; i++) {
            answer += s.charAt(p + (i * m) - 1);
        }
        
        return answer;
    }
}