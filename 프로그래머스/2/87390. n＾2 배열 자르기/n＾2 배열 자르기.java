import java.util.*;

class Solution {
    int[] makeRowArray(int n, int row) {
        int[] arr = new int[n];
        int num = row + 1;

        for(int i = 0; i < n; i++) {
            if(i < row) {
                arr[i] = num;
            } else {
                arr[i] = num++;
            }

        }

        return arr;
    }

    public int[] solution(int n, long left, long right) {
        
        int sx = (int) (left / n);
        int sy = (int) (left % n);
        int ex = (int) (right / n);
        int ey = (int) (right % n);

        List<Integer> answer = new ArrayList<>();
        int[] arr = makeRowArray(n, sx);
        
        // [예외] 만약 left와 right가 동일 행인 경우
        if(sx == ex) {
            for(int i = sy; i <= ey; i++) {
                answer.add(arr[i]);
            }
            
            return answer.stream().mapToInt(i -> i).toArray();
        }

        // sx행
        for(int i = sy; i < n; i++) {
            answer.add(arr[i]);
        }

        // sx + 1행 ~ ex - 1행 전체 선택
        for(int i = sx + 1; i < ex; i++) {
            arr = makeRowArray(n, i);
            for (int j = 0; j < n; j++) {
                answer.add(arr[j]);
            }
        }

        // ex행
        arr = makeRowArray(n, ex);
        for(int i = 0; i <= ey; i++) {
            answer.add(arr[i]);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
