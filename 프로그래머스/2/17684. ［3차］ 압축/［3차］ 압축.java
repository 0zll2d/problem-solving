import java.util.*;

class Solution {
    public int[] solution(String msg) {

        Map<String, Integer> dic = new HashMap<>();

        for(int i = 0; i < 26; i++) {
            String alphabet = String.valueOf((char)('A' + i));  // char -> String에 저장은 가능하지만 가장 첫 문자를 문자열 변환 필요
            dic.put(alphabet, i + 1);
        }

        int start = 0;
        int num = 27;
        List<Integer> answer = new ArrayList<>();

        while (start < msg.length()) {
            int end = start;
            String word = "" + msg.charAt(start);

            // 최대 단어 찾기
            while (end + 1 < msg.length() && dic.containsKey(word + msg.charAt(end + 1))) {
                word += msg.charAt(++end);
            }

            // 출력 (가장 긴 기존 단어)
            answer.add(dic.get(word));

            // 사전 등록 (word + 다음 문자)
            if (end + 1 < msg.length()) {
                String newWord = word + msg.charAt(end + 1);
                dic.put(newWord, num++);
            }

            // 다음 시작 위치
            start = end + 1;
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}