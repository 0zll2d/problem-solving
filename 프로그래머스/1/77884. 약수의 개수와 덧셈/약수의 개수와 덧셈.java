class Solution {
    public int solution(int left, int right) {
        int answer = 0;

        for (int i = left; i <= right; i++) {
            answer += (countDivisors(i) % 2 == 0) ? i : -i;
        }

        return answer;
    }

    private int countDivisors(int num) {
        int count = 0;

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                count += 2;
                if (i * i == num) {
                    count--; // 제곱수 보정
                }
            }
        }

        return count;
    }
}