class Solution {
    public boolean isPalindrome(String s) {
        
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()) {
            // 숫자 or 문자 확인: isLetterOrDigit()
            if(Character.isLetterOrDigit(c)) {
                if(Character.isUpperCase(c)) {
                    c = Character.toLowerCase(c);
                }
                sb.append(c);
            }
        }

        return sb.toString().equals(sb.reverse().toString());
    }
}