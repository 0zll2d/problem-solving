import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] answer = new String[players.length];
        
        Map<String, Integer> player = new HashMap<>();
        Map<Integer, String> rank = new HashMap<>();
        
        for(int i = 0; i < players.length; i++) {
            player.put(players[i], i);
            rank.put(i, players[i]);
        }
        
        for(String calling : callings) {
            int playerRank = player.get(calling);   // 이름 불린 사람의 순위
            String switchPlayer = rank.get(playerRank - 1);     // 이름 불린 사람과 랭크가 바뀔 사람
                        
            // 플레이어 변경
            player.put(calling, playerRank - 1);
            player.put(switchPlayer, playerRank);
            
            // 순위 변경
            rank.put(playerRank, switchPlayer);
            rank.put(playerRank - 1, calling);
        }
        
        for(int i = 0; i < players.length; i++) {
            answer[i] = rank.get(i);
        }
        
        return answer;
    }
}