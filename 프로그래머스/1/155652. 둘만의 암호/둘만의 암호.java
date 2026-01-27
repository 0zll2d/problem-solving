class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder answer = new StringBuilder();
        boolean[] isSkip = new boolean[26];

        for(int i = 0; i < skip.length(); i++) {
            char c = skip.charAt(i);
            isSkip[c - 'a'] = true;
        }

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int j = 0;

            while(j < index) {
                c = (char) ('a' + (c - 'a' + 1) % 26);
                if(isSkip[c - 'a']) {
                    continue;
                }
                j++;
            }

            answer.append(c);
        }

        return answer.toString();
    }
}