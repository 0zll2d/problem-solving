import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        
        int[] clothes = new int[n + 1];
        Arrays.fill(clothes, 1);
        clothes[0] = 0;
        
        // 체육복 도난당한 학생
        for(int num : lost) {
            clothes[num]--;
        }
        
        // 여벌 체육복 있는 학생
        for(int num : reserve) {
            clothes[num]++;
        }
        
        // [중요] 처리 순서에 따라 결과 값이 달라지므로 정렬 필요(예: n = 5, lost = [4, 2], reserve = [3, 5])
        Arrays.sort(lost);
        
        // 체육복 빌려주기
        for(int num : lost) {
            int prev = num - 1;
            int next = num + 1;
            
            // [중요] 여벌(+1) + 도난(-1) both인 경우는 고려하지 않음
            if(clothes[num] == 1) {
                continue;
            }
            
            // 앞 학생 먼저 확인 -> 그 다음 뒤 학생 확인
            if(prev > 0 && clothes[prev] > 1) {
                clothes[prev]--;
                clothes[num]++;
            } else if(next <= n && clothes[next] > 1) {
                clothes[next]--;
                clothes[num]++;
            }
        }
        
        for(int i = 1; i <= n; i++) {
            if(clothes[i] > 0) {
                answer++;
            }
        }
        
        return answer;
    }
}