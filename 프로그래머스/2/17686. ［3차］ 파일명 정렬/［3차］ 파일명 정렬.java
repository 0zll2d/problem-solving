import java.util.*;

class Solution {
    
    static class File {
        String head;
        int number;
        int index;
        
        File(String head, int number, int index) {
            this.head = head;
            this.number = number;
            this.index = index;
        }
    }
    
    public String[] solution(String[] files) {
        String[] answer = new String[files.length];
        
        List<File> fileList = new ArrayList<>();
        
        for(int i = 0; i < files.length; i++) {
            // 1. NUMBER 시작 인덱스 찾기
            int si = 0;
            
            while(si < files[i].length() && !Character.isDigit(files[i].charAt(si))) {
                si++;
            }
            
            // 2. NUMBER 끝 인덱스 찾기
            int ei = si;
            
            while(ei < files[i].length() && Character.isDigit(files[i].charAt(ei))) {
                ei++;
            }
            
            // 3. HEAD, NUMBER 추출
            String head = files[i].substring(0, si).toLowerCase();
            int number = Integer.parseInt(files[i].substring(si, ei));
            
            fileList.add(new File(head, number, i));
        }
        
        // 파일명 정렬
        fileList.sort((f1, f2) -> {
            if(f1.head.equals(f2.head)){
                if(f1.number == f2.number) {
                    return Integer.compare(f1.index, f2.index);
                }
                return Integer.compare(f1.number, f2.number);
            }
            return f1.head.compareTo(f2.head);
        });
        
        for(int i = 0; i < fileList.size(); i++) {
            int index = fileList.get(i).index;
            answer[i] = files[index];
        }
        
        return answer;
    }
}