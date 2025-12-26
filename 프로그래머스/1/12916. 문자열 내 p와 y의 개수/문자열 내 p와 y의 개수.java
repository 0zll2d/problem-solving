class Solution {
    boolean solution(String s) {
        String lower = s.toLowerCase();     
        int p = 0;
        int y = 0;
        
        for(int i = 0; i < lower.length(); i++) {
            if(lower.charAt(i) == 'p') {
                p++;
            }
            if(lower.charAt(i) == 'y') {
                y++;
            }
        }

        return p == y;
    }
}