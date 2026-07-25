import java.util.*;

class Solution {
     
    public String[] solution(String[] record) {
        
        final String IN = "님이 들어왔습니다.";
        final String OUT = "님이 나갔습니다.";
        
        Map<String, String> users = new HashMap<>(); // {아이디, 닉네임}
        List<String[]> rooms = new ArrayList<>();   // [아이디, 출입여부]
        
        for(String r : record) {
            String[] word = r.split(" ");
            String op = word[0];
            String id = word[1];
            
            if("Leave".equals(op)) {
                rooms.add(new String[]{id, OUT});
                continue;
            }
            
            // "Enter" 혹은 "Change"
            String nickname = word[2];
            
            users.put(id, nickname);
            
            if("Enter".equals(op)) {
                rooms.add(new String[]{id, IN});
            }
        }
        
        String[] answer = new String[rooms.size()];
        
        for(int i = 0; i < rooms.size(); i++) {
            answer[i] = users.get(rooms.get(i)[0]) + rooms.get(i)[1];
        }
        
        return answer;
    }
}