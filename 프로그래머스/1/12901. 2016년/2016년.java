class Solution {
    public String solution(int a, int b) {
        int[] month = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] days = {"THU", "FRI", "SAT", "SUN", "MON", "TUE", "WED"};

        int sum = b;
        for(int i = 1; i < a; i++) {
            sum += month[i];
        }

        return days[sum % 7];
    }
}