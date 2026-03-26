class Solution {
    int answer = 0;

    void dfs(int[] numbers, int target, int idx, int sum) {
        if(idx == numbers.length) {
            if(sum == target) {
                answer++;
            }
            return;
        }

        // 더하기
        dfs(numbers, target, idx + 1, sum + numbers[idx]);

        // 빼기
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }

    public int solution(int[] numbers, int target) {

        dfs(numbers, target, 0, 0);

        return answer;
    }
}