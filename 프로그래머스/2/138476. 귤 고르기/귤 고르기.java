import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;

        Map<Integer, Integer> m = new HashMap<>();

        for(int t : tangerine) {
            m.put(t, m.getOrDefault(t, 0) + 1);
        }

        List<Tangerine> tangerineList = new ArrayList<>();

        for(int key : m.keySet()) {
            tangerineList.add(new Tangerine(key, m.get(key)));
        }

        Collections.sort(tangerineList, (o1, o2) -> o2.count - o1.count);

        for(Tangerine t : tangerineList) {
            if(k <= 0) {
                break;
            }

            k -= t.count;
            answer++;
        }

        return answer;
    }

    static class Tangerine {
        int type;
        int count;

        Tangerine(int type, int count) {
            this.type = type;
            this.count = count;
        }
    }
}