class Solution {
    public String solution(String new_id) {

        // 1단계: 소문자 변환
        String answer = new_id.toLowerCase();

        // 2단계: 허용 문자 외 제거
        answer = answer.replaceAll("[^a-z0-9-_.]", "");

        // 3단계: 연속된 마침표 하나로
        answer = answer.replaceAll("\\.+", ".");

        // 4단계: 처음/끝 마침표 제거
        answer = answer.replaceAll("^\\.|\\.$", "");

        // 5단계: 빈 문자열이면 "a"
        if (answer.isEmpty()) {
            answer = "a";
        }

        // 6단계: 길이 15 제한 + 끝 마침표 제거
        if (answer.length() >= 16) {
            answer = answer.substring(0, 15).replaceAll("\\.$", "");
        }

        // 7단계: 길이 3 될 때까지 마지막 문자 반복
        while (answer.length() < 3) {
            answer += answer.charAt(answer.length() - 1);
        }

        return answer;
    }

}