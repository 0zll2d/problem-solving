class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        for(int i = 1; i <= number; i++) {
            int factor = getFactors(i);
            answer += factor > limit ? power : factor;
        }

        return answer;
    }

    private int getFactors(int number) {
        int count = 0;

        for(int i = 1; i * i <= number; i++) {
            if(number % i == 0) {
                count += 2;
                if(i * i == number) {
                    count--;
                }
            }
        }

        return count;
    }
}