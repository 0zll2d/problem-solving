class Solution {
    public boolean solution(String s) {

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        
        return s.length() == 4 || s.length() == 6;
    }
}