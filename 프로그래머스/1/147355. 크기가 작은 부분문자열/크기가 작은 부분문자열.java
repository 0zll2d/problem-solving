class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        
        for(int i = 0; i <= t.length() - p.length(); i++) {
            String tSub = t.substring(i, p.length() + i);
            if(tSub.compareTo(p) <= 0) {
                answer++;
            }
        }
        
        return answer;
    }
}

// Java의 부분 문자열
// String substring(int beginIndex, int endIndex);
// [beginIndex, endIndex)

// 런타임 에러가 발생하는 이유?
// p의 길이가 18인 경우, Integer.parseInt() 시 int의 범위를 벗어남
// 따라서 문자열 비교 수행

// int compareTo(String anotherString);
// 결과값
// - 음수(< 0): 첫 문자열이 작다
// - 0: 두 문자열이 같다
// - 양수(> 0): 첫 문자열이 크다

