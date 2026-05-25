import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        Map<Character, Integer> m = new HashMap<>();
        
        for(int i = 0; i < skill.length(); i++) {
            m.put(skill.charAt(i), i);
        }
        
        for(String tree : skill_trees) {
            boolean possible = true;
            int idx = 0;
            
            for(char c : tree.toCharArray()) {
                if(!m.containsKey(c)) {
                    continue;
                }
                // 선행 스킬 순서가 아니면
                if(m.get(c) != idx) {
                    possible = false;
                    break;
                }
                // 선행 스킬 순서를 따르면
                idx++;
            }
            
            if(possible) {
                answer++;
            }
        }
        
        return answer;
    }
}


