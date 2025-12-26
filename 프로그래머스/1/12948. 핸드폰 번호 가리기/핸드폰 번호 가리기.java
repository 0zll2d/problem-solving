class Solution {
    public String solution(String phone_number) {
        String answer = "";
        int idx = phone_number.length() - 4;
        
        for(int i = 0; i < idx; i++) {
            answer += "*";
        }
        
        return answer + phone_number.substring(idx);
    }
}

