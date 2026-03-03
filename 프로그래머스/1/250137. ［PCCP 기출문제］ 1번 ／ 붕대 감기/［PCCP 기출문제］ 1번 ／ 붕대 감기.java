class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {

        int t = bandage[0];
        int x = bandage[1];
        int y = bandage[2];

        int count = 0;  // 연속 성공 횟수
        int maxHealth = health;
        int idx = 0;

        for(int time = 1; time <= attacks[attacks.length - 1][0]; time++) {
            if(time == attacks[idx][0]) {
                health -= attacks[idx][1];
                if(health <= 0) {
                    return -1;
                }
                count = 0;
                idx++;
            } else {
                health += x;
                count++;

                if(count == t) {
                    health += y;
                    count = 0;
                }

                // 추가 체력 회복
                if(health > maxHealth) {
                    health = maxHealth;
                }
            }
        }

        return health;
    }
}