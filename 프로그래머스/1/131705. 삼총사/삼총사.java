class Solution {
    private int answer = 0;
    
    public int solution(int[] number) {
        makeTrio(number, 0, 0, 0);
        return answer;
    }
    
    private void makeTrio(int[] number, int index, int sum, int count) {
        // 3개를 이미 골랐으면 여기서 판정
        if (count == 3) {
            if (sum == 0) {
                answer++;
            }
            return;
        }

        // 끝까지 갔는데 3개 못 고른 경우
        if (index == number.length) {
            return;
        }
        
        // 선택 O
        makeTrio(number, index + 1, sum + number[index], count + 1);
        
        // 선택 X
        makeTrio(number, index + 1, sum, count);
    }
}