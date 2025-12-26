class Solution {
    public String solution(String[] seoul) {
        int idx;
        
        for(idx = 0; idx < seoul.length; idx++) {
            if("Kim".equals(seoul[idx])) {
                break;
            }
        }
        
        return "김서방은 " + String.valueOf(idx) + "에 있다";
    }
}

// [Java에서의 문자열 비교]
// == 는 객체 주소 비교
// equals() 는 문자열 비교