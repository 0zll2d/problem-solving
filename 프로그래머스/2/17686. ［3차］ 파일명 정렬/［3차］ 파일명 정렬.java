import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];

        // 1. 파일 파싱
        List<File> fileList = new ArrayList<>();

        for(int i = 0; i < files.length; i++) {
            int start = 0;  // 숫자 시작 인덱스

            while(start < files[i].length() && !Character.isDigit(files[i].charAt(start))) {
                start++;
            }

            int end = start;    // 숫자 끝 인덱스 + 1

            while(end < files[i].length() && Character.isDigit(files[i].charAt(end))) {
                end++;
            }

            String head = files[i].substring(0, start);
            String number = files[i].substring(start, end);
            String tail = files[i].substring(end);

            fileList.add(new File(i, head.toLowerCase(), Integer.parseInt(number), tail.toLowerCase()));  // head 대소문자 구분 없음 + number 숫자 변환
        }

        // 2. 파일명 정렬 수행
        Collections.sort(fileList, (f1, f2) -> {
            if(f1.head.equals(f2.head)) {   // HEAD 동일 시 NUMBER 순 정렬
                if(f1.number ==  f2.number) {   // NUMBER까지 동일 시 기존 입력 순서(index) 유ㅈ
                    return f1.index - f2.index;
                }
                return f1.number - f2.number;
            }

            return f1.head.compareTo(f2.head);  // HEAD 기분 사전순 정렬
        });

        for(int i = 0; i < fileList.size(); i++) {
            answer[i] = files[fileList.get(i).index];
        }

        return answer;
    }

    static class File {
        int index;
        String head;
        int number;
        String tail;

        File(int index, String head, int number, String tail) {
            this.index = index;
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }
}