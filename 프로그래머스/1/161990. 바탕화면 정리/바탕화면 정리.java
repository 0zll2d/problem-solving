class Solution {
    public int[] solution(String[] wallpaper) {
        int lux = wallpaper.length;
        int luy = wallpaper[0].length();

        int rdx = 0;
        int rdy = 0;

        for(int i = 0; i < wallpaper.length; i++) {
            for(int j = 0; j < wallpaper[i].length(); j++) {
                if(wallpaper[i].charAt(j) == '#') {
                    if(lux > i) {
                        lux = i;
                    }
                    if(luy > j) {
                        luy = j;
                    }
                    if(rdx < i) {
                        rdx = i;
                    }
                    if(rdy < j) {
                        rdy = j;
                    }
                }
            }
        }

        return new int[] {lux, luy, rdx + 1, rdy + 1};
    }
}