class Solution {
    public String solution(String phone_number) {
        int idx = phone_number.length() - 4;
        String answer = "*".repeat(idx);
        
        return answer + phone_number.substring(idx);
    }
}

// [ Java에서 문자열 반복 (Java 11+) ]
// String.repeat(int count)
 
