import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {

        int height = (n + w - 1) / w; // 전체 층 수

        int[][] boxPosition = new int[n + 1][2]; // 각 박스의 위치 [row, col]
        List<List<Integer>> columnBoxes = new ArrayList<>(); // 열별 박스 번호 저장

        for (int col = 0; col < w; col++) {
            columnBoxes.add(new ArrayList<>());
        }

        int boxNumber = 1;

        for (int row = 0; row < height; row++) {

            if (row % 2 == 0) { // 왼 -> 오른
                for (int col = 0; col < w && boxNumber <= n; col++) {
                    boxPosition[boxNumber][0] = row;
                    boxPosition[boxNumber][1] = col;

                    columnBoxes.get(col).add(boxNumber);
                    boxNumber++;
                }

            } else { // 오른 -> 왼
                for (int col = w - 1; col >= 0 && boxNumber <= n; col--) {
                    boxPosition[boxNumber][0] = row;
                    boxPosition[boxNumber][1] = col;

                    columnBoxes.get(col).add(boxNumber);
                    boxNumber++;
                }
            }
        }

        int targetRow = boxPosition[num][0];
        int targetCol = boxPosition[num][1];

        return columnBoxes.get(targetCol).size() - targetRow;
    }

}