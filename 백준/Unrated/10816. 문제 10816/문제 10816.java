import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arrs = new int[N];

        for(int i = 0; i < N; i++) {
            arrs[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] nums = new int[M];

        for(int i = 0; i < M; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[M];

        // 이분탐색을 위한 정렬
        Arrays.sort(arrs);

        for(int i = 0; i < M; i++) {
            int target = nums[i];

            int left = 0;
            int right = N - 1;

            // [1] lower_bound: target 값보다 크거나 같은 index 찾기
            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arrs[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            // target 값이 arrs 배열 내 존재하지 않으면 0 반환
            // Edge Case: arrs 배열 내 값보다 크면 left == N 이 경우를 잡지 않으면 ArrayIndexOutOfBoundsException 발생
            if(left == N || target != arrs[left]) {
                answer[i] = 0;
                continue;
            }

            int lb = left;  // lower_bound의 index 값

            // [2] upper_bound: target 값보다 큰 첫번째 index 찾기
            left = 0;
            right = N - 1;

            while(left <= right) {
                int mid = left + (right - left) / 2;

                if(arrs[mid] <= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            answer[i] = left - lb;
        }

        for(int i = 0; i < M; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
