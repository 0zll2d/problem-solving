import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i = 0; i < commands.length; i++) {
            int from = commands[i][0] - 1;
            int to = commands[i][1];
            int k = commands[i][2] - 1;

            int[] copied = Arrays.copyOfRange(array, from, to);
            Arrays.sort(copied);

            answer[i] = copied[k];
        }
        return answer;
    }
}
/**
 * [ Java 배열 복사 ]
 *
 * 1. Arrays.copyOfRange(int[] original, int from, int to)
 *    - original 배열의 [from, to) 구간을 부분 복사
 *    - to 인덱스는 포함되지 않음
 *
 * 2. Arrays.copyOf(int[] original, int length)
 *    - original 배열을 length 길이만큼 복사
 *    - length가 원본보다 크면 남는 값은 0으로 채움
 *
 * 3. 2차원 배열 복사
 *    - clone()은 겉 배열(Object)만 복사됨 (얕은 복사)
 *    - 깊은 복사를 위해 for문 내에서 arr[i].clone() 사용
 */
