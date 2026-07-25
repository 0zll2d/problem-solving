import java.util.*;

class Solution {
    
    int diff;   // 라이언이 이길 수 있는 최대 점수 차
    int[] answer;
    
    public int[] solution(int n, int[] info) {
        
        diff = -1;
        answer = new int[11];
        
        // DFS로 모든 가능한 화살 배치 탐색
        dfs(n, info, new int[11], 0, 0, 0);
        
        // 이길 수 있는 경우가 없으면 [-1] 반환
        if(diff == -1) {
            return new int[]{-1};
        }
                
        return answer;
    }
    
    void dfs(int n, int[] apeach, int[] ryan, int idx, int ascore, int rscore) {
        // 마지막 과녁(0점)은 점수에 영향을 주지 않으므로 남은 화살만 모두 배치
        if(idx == 10) {
            ryan[10] = n;
            
            // 라이언이 이긴 경우만 정답 후보
            if(ascore < rscore) {
                int result = rscore - ascore;
                
                // 더 큰 점수 차라면 정답 갱신
                if(diff < result) {
                    diff = result;
                    answer = ryan.clone();   // 현재 상태 저장, clone: 특정 시점에 배열의 스냅샷(answer = ryan, 참조 객체 공유)
                }
                // 점수 차가 같다면 낮은 점수를 더 많이 맞힌 경우 선택
                else if(diff == result) {
                    answer = getLower(answer, ryan).clone();
                }
            }
            
            return;
        }
     
        // 현재 점수를 가져가기 위해 어피치보다 1발 더 쏘는 경우
        int arrows = apeach[idx] + 1;
        
        if(n - arrows >= 0) {
            ryan[idx] = arrows;
            dfs(n - arrows, apeach, ryan, idx + 1, ascore, rscore + 10 - idx);
        }
        
        // 현재 점수를 포기하는 경우
        ryan[idx] = 0;
        if(apeach[idx] > 0) {
            ascore += 10 - idx;
        }
        dfs(n, apeach, ryan, idx + 1, ascore, rscore);
    }
    
    // 점수 차가 같다면 더 낮은 점수에 화살을 많이 맞힌 경우 선택
    int[] getLower(int[] r1, int[] r2) {
        for(int i = 10; i >= 0; i--) {
            if(r1[i] > r2[i]) {
                return r1;
            } else if(r1[i] < r2[i]) {
                return r2;
            }
        }
        return r1;
    }
}