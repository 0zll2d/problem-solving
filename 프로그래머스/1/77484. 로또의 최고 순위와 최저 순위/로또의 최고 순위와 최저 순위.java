class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int hidden = 0; // 숨김 개수
        int correct = 0;    // 일치 개수

        for(int i = 0; i < 6; i++) {
            if(lottos[i] == 0) {
                hidden++;
                continue;
            }

            for(int j = 0; j < 6; j++) {
                if(lottos[i] == win_nums[j]) {
                    correct++;
                    break;
                }
            }
        }

        // 일치 개수별 순위
        int[] rank = {6, 6, 5, 4, 3, 2, 1};

        return new int[]{rank[correct + hidden], rank[correct]};
    }
}