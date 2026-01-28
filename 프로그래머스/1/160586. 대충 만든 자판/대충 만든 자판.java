import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        int[] answer = new int[targets.length];

        Map<Character, Integer> m = new HashMap<>();

        for(String key : keymap) {
            for(int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                m.put(c, Math.min(m.getOrDefault(c, i + 1), i + 1));
            }
        }

        for(int i = 0; i < targets.length; i++) {
            for(int j = 0; j < targets[i].length(); j++) {
                char c = targets[i].charAt(j);

                if(!m.containsKey(c)) {
                    answer[i] = -1;
                    break;
                }
                
                answer[i] += m.get(c);
            }
        }

        return answer;
    }
}