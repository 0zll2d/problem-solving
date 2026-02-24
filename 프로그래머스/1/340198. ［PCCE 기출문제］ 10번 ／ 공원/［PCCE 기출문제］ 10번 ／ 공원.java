import java.util.*;

class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;

        int h = park.length;
        int w = park[0].length;

        Arrays.sort(mats);  // 매트 오름차순 정렬

        for(int i = 0; i < h; i++) {  // 50
            for(int j = 0; j < w; j++) {   // 50
                if("-1".equals(park[i][j])) {
                    for(int mat : mats) {   // 10 -> answer 값에 바로 최댓값 저장하지 않고, 인덱스를 저장한다면, 해당 인덱스부터 검사 가능
                        if(isOutOfBounds(h, w, mat, i, j)) {    // 경계값 체크
                            break;
                        }
                        if(check(mat, park, i, j)) {    // 50 * 50
                            answer = Math.max(answer, mat);
                        }
                    }
                }
            }
        }

        return answer == 0 ? -1 : answer;
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