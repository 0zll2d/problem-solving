class Solution {
    boolean solution(String s) {

        int diff = 0;
        
        s = s.toLowerCase();
        
        for(char c : s.toCharArray()) {
            if(c == 'p') {
                diff++;
            } else if(c == 'y') {
                diff--;
            }
        }

        return diff == 0;
    }
}