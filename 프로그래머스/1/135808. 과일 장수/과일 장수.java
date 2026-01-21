import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < score.length; i++){
            list.add(score[i]);
        }

        Collections.sort(list, Collections.reverseOrder());

        for(int i = m - 1; i < list.size(); i += m){
            answer += list.get(i) * m;
        }

        return answer;
    }
}