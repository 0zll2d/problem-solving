class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int area = brown + yellow;  // 너비
        
        for(int height = 3; height * height <= area; height++) { // brown 최소 8, yellow 최소 1 = 최소 3 x 3
            if(area % height == 0) {
                int width = area / height;
                
                if(width * 2 + height * 2 - 4 == brown) {   // 둘레(테두리) = brown의 개수
                    answer = new int[]{width, height};
                    break;
                }
            }
        }
        
        return answer;
    }
}