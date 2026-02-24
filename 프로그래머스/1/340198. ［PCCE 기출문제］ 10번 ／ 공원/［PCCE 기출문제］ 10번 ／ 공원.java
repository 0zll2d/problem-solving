import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int h = park.length;
        int w = park[0].length;

        Arrays.sort(mats);  // 매트 오름차순 정렬

        int idx = -1;    // 가장 큰 매트의 인덱스

        for(int i = 0; i < h; i++) {  // 50
            for(int j = 0; j < w; j++) {   // 50
                if("-1".equals(park[i][j])) {
                    for(int k = idx + 1; k < mats.length; k++) {   // 가장 큰 매트의 인덱스를 저장하고, 해당 인덱스 다음 더 큰 매트부터 반복문 순회하며 검사
                        if(isOutOfBounds(h, w, mats[k], i, j)) {    // 경계값 체크
                            break;
                        }
                        if(check(mats[k], park, i, j)) {    // 50 * 50
                            idx = k;
                        }
                    }
                }
            }
        }

        return idx == -1 ? idx : mats[idx];
    }

    private boolean check(int mat, String[][] park, int x, int y) {
        for(int i = 0; i < mat; i++) {
            for(int j = 0; j < mat; j++) {
                if(!park[x + i][y + j].equals("-1")) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOutOfBounds(int height, int width, int mat, int x, int y) {
        return (x + mat > height || y + mat > width);   // 거리가 아니고 정사각형의 개수이므로 '>='이 아닌 '>' 비교
    }
}