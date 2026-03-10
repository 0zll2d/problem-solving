import java.util.*;

class Solution {
    public int solution(String message, int[][] spoiler_ranges) {
        int answer = 0;

        // 1. 스포 방지 구간 찾기
        boolean[] spoiled = new boolean[message.length()];

        for(int[] range : spoiler_ranges) {
            for(int i = range[0]; i <= range[1]; i++) {
                spoiled[i] = true;
            }
        }

        // 2. 스포 방지 구간이 아닌 구간의 속한 단어 찾기
        Set<String> not_spoiled_words = new HashSet<>();
        int idx = 0;
        boolean isSpoiled = false;

        for(int i = 0; i < message.length(); i++) {
            if(message.charAt(i) == ' ') {
                if(!isSpoiled) {
                    not_spoiled_words.add(message.substring(idx, i));
                }
                idx = i + 1;
                isSpoiled = false;
            } else if(spoiled[i]) {
                isSpoiled = true;
            }
        }

        if(idx < message.length() && !isSpoiled) {
            not_spoiled_words.add(message.substring(idx));
        }

        // 3. 스포 방지 단어 찾아 중요 단어인지 판별하기
        Set<String> spoiled_words = new HashSet<>();    // 이전에 공개된 스포 방지 단어인지 확인용

        for (int[] range : spoiler_ranges) {

            int start = range[0];
            int end = range[1];

            // 단어 시작 찾기 (왼쪽 확장)
            while (start > 0 && message.charAt(start - 1) != ' ') {
                start--;
            }

            // 단어 끝 찾기 (오른쪽 확장)
            while (end < message.length() - 1 && message.charAt(end + 1) != ' ') {
                end++;
            }

            String sub_message = message.substring(start, end + 1); // 부분 문자열 파악
            String[] words = sub_message.split(" ");    // 스포일러 방지 단어

            // 3.2. 스포일러 방지 단어에서 중요 단어 찾기
            for(String word : words) {
                // 2.2.1. 메세지 스포 방지 구간이 아닌 구간에 속하지 않는지 확인
                if(not_spoiled_words.contains(word)) {
                    continue;
                }

                // 2.2.2. 이전에 공개된 스포 방지 단어와 중복되지 않는지 확인
                if(spoiled_words.contains(word)) {
                    continue;
                }

                spoiled_words.add(word);
                answer++;
            }
        }

        return answer;
    }
}