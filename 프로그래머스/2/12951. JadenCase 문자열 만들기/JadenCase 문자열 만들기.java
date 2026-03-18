class Solution {
    public String solution(String s) {
        
        // 1. 문자열 전체 소문자로 변환        
        s = s.toLowerCase();   
 
        // 2. JadenCase 문자열 만들기
        StringBuilder sb = new StringBuilder();
 
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            // 단어의 첫 문자가 알파벳 소문자이면 대문자로 변환
            if((i == 0 || s.charAt(i - 1) == ' ') && Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
            } 
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}