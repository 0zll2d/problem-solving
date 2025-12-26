class Solution {
    public String solution(String s) {
        String answer = "";
        int len = s.length();
        
        return (len % 2 == 0) ?
                answer + s.substring(len / 2 - 1, len / 2 + 1) :
                answer + s.charAt(len / 2);
    }
}