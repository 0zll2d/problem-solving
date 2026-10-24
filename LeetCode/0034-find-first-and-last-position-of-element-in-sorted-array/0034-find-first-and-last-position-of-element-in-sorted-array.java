class Solution {
    public int[] searchRange(int[] nums, int target) {

        int lb = -1;    // [Lower Bound] target 이상인 첫 인덱스

        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        lb = left;

        int ub = -1;    // [Upper Bound] target 초과인 첫 인덱스

        left = 0;
        right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        ub = left - 1;

        return lb > ub ? new int[]{-1, -1} : new int[]{lb, ub};
    }
}