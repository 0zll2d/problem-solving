class Solution {
    public String solution(String s) {
        
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        
        for(char c : s.toCharArray()) {
            if(c == ' ') {
                idx = 0;
            } else {
                if(idx % 2 == 0) {
                    c = Character.toUpperCase(c);
                } else {
                    c = Character.toLowerCase(c);
                }
                idx++;
            }
            sb.append(c);
        }
        
        return sb.toString();
    }
}