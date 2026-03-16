import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> m = new HashMap<>();

        for(int t : tangerine) {
            m.put(t, m.getOrDefault(t, 0) + 1);
        }
        
        /*귤의 타입별 개수는 이미 Map에서 한 번 걸렀으니
        결국 귤의 타입은 중요하지 않고 개수만 중요함*/
        List<Integer> tangerineList = new ArrayList<>(m.values());

        Collections.sort(tangerineList, (o1, o2) -> o2 - o1);

        for(int t : tangerineList) {
            if(k <= 0) {
                break;
            }

            k -= t;
            answer++;
        }

        return answer;
    }
}