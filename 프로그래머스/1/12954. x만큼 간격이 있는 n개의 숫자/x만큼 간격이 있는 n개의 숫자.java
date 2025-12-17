class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        
        for(int i = 0; i < n; i++) {
            answer[i] = (long) x * (i + 1);
        }
        
        return answer;
    }
}

// 왜 long으로 형변환이 필요할까?
// x와 i + 1 모두 int 형이기 때문에 int형의 결과값이 long으로 변환이 됨
// x = 10,000,000(천 만),  n = 1000일 때
// 10,000,000,000(백 억)으로 int의 범위를 초과해 overflow 발생하고, long으로 변환 시 이상한 값이 저장됨