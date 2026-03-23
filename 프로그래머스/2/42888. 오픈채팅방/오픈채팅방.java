import java.util.*;

class Solution {
    public String[] solution(String[] record) {

        final String enterMsg = "님이 들어왔습니다.";
        final String leaveMsg = "님이 나갔습니다.";

        Map<String, String> user = new HashMap<>();
        List<Log> logs = new ArrayList<>();

        for(String rec : record) {
            String[] words = rec.split(" ");
            String action = words[0];
            String id = words[1];

            if("Leave".equals(action)) {
                logs.add(new Log(id, leaveMsg));
                continue;
            }

            String name = words[2];

            if("Enter".equals(action)) {
                logs.add(new Log(id, enterMsg));
            }

            user.put(id, name); // 유저 정보 추가(Enter) 및 덮어쓰기(Change)
        }

        String[] answer = new String[logs.size()];
        int idx = 0;

        for(Log log : logs) {
            String msg = user.get(log.id) + log.action;
            answer[idx++] = msg;
        }

        return answer;
    }

    static class Log {
        String id;
        String action;

        Log(String id, String action) {
            this.id = id;
            this.action = action;
        }
    }

}