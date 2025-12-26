class Solution {
    public int solution(int[] numbers) {
        int answer = 9 * 10 / 2;   // 0 ~ 9까지 합
        
        for(int num : numbers) {
            answer -= num;
        }
        
        return answer;
    }
}

// 등차수열의 합 공식 활용
// S = n * (n + 1) / 2;