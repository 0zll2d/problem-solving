class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[][] arr = new int[nums.length][2];  // {nums[i], i}

        // 투포인터 오름차순 정렬
        for(int i = 0; i < nums.length; i++) {
            arr[i] = new int[]{nums[i], i};
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int i = 0;
        int j = arr.length - 1;
        int[] answer = new int[2];

        while(i < j) {
            if(arr[i][0] + arr[j][0] == target) {
                answer = new int[] {arr[i][1], arr[j][1]};
                break;
            } else if(arr[i][0] + arr[j][0] < target) {
                i++;
            } else {
                j--;
            }
        }

        return answer;
    }
}