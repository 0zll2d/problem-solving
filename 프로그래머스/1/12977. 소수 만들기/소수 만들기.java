class Solution {
    public int solution(int[] nums) {
        int answer = 0;

        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length; j++){
                for(int k = j + 1; k < nums.length; k++){
                    if(isPrime(nums[i]+nums[j]+nums[k])){
                        answer++;
                    }
                }
            }
        }
        return answer;
    }

    // 소수와 약수의 관계: 소수는 약수의 개수가 1과 본인 밖에 없기 때문에 약수 개수 판별 알고리즘 사용 가능
    private boolean isPrime(int num) {
        // 만약 num이 2인 경우 소수이므로 true 필요, 하지만 세 값을 더한 수는 항상 1보다 큼
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) {
                return false;
            }
        }

        return true;
    }
}