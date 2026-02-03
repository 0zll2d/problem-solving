import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {

        // 숫자별 키패드 좌표
        Map<Integer, int[]> keyPosition = new HashMap<>();
        keyPosition.put(1, new int[]{0, 0});
        keyPosition.put(2, new int[]{0, 1});
        keyPosition.put(3, new int[]{0, 2});
        keyPosition.put(4, new int[]{1, 0});
        keyPosition.put(5, new int[]{1, 1});
        keyPosition.put(6, new int[]{1, 2});
        keyPosition.put(7, new int[]{2, 0});
        keyPosition.put(8, new int[]{2, 1});
        keyPosition.put(9, new int[]{2, 2});
        keyPosition.put(0, new int[]{3, 1});

        // 숫자별 기본 손 규칙 (L / R / M)
        Map<Integer, Character> defaultHandRule = new HashMap<>();
        defaultHandRule.put(1, 'L');
        defaultHandRule.put(4, 'L');
        defaultHandRule.put(7, 'L');
        defaultHandRule.put(3, 'R');
        defaultHandRule.put(6, 'R');
        defaultHandRule.put(9, 'R');
        defaultHandRule.put(2, 'M');
        defaultHandRule.put(5, 'M');
        defaultHandRule.put(8, 'M');
        defaultHandRule.put(0, 'M');

        StringBuilder result = new StringBuilder();

        // 현재 왼손 / 오른손 위치
        int[] leftHandPos = new int[]{3, 0};   // *
        int[] rightHandPos = new int[]{3, 2};  // #

        // 사용자의 주 손
        char preferredHand = hand.equals("left") ? 'L' : 'R';

        for (int number : numbers) {
            char selectedHand = defaultHandRule.get(number);
            int[] targetPos = keyPosition.get(number);

            // 가운데 숫자일 경우 거리 계산
            if (selectedHand == 'M') {
                int leftDistance =
                        Math.abs(leftHandPos[0] - targetPos[0]) +
                        Math.abs(leftHandPos[1] - targetPos[1]);

                int rightDistance =
                        Math.abs(rightHandPos[0] - targetPos[0]) +
                        Math.abs(rightHandPos[1] - targetPos[1]);

                if (leftDistance == rightDistance) {
                    selectedHand = preferredHand;
                } else {
                    selectedHand = leftDistance < rightDistance ? 'L' : 'R';
                }
            }

            // 손 위치 갱신
            if (selectedHand == 'L') {
                leftHandPos = targetPos;
            } else {
                rightHandPos = targetPos;
            }

            result.append(selectedHand);
        }

        return result.toString();
    }
}