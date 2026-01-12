import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> m = new HashMap<>();
        m.put('R', 0);
        m.put('T', 0);
        m.put('C', 0);
        m.put('F', 0);
        m.put('J', 0);
        m.put('M', 0);
        m.put('A', 0);
        m.put('N', 0);

        int[] scores = {0, 3, 2, 1, 0, 1, 2, 3};

        for(int i = 0; i < survey.length; i++) {
            char first = survey[i].charAt(0);
            char second = survey[i].charAt(1);
            int choice = choices[i];

            if(choices[i] < 4) {
                m.put(first, m.get(first) + scores[choice]);
            } else if(choices[i] > 4) {
                m.put(second, m.get(second) + scores[choice]);
            }
        }

        String[] types = {"RT", "CF", "JM", "AN"};
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 4; i++) {
            char first = types[i].charAt(0);
            char second = types[i].charAt(1);

            sb.append(m.get(first) >= m.get(second) ? first : second);
        }

        return sb.toString();
    }

}