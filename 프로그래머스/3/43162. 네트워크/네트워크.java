// 유니온 파인드 - 같은 집합인지 찾기
import java.util.*;

class Solution {
    
    int[] parent;
    int answer; // 그룹의 개수
    
    public int solution(int n, int[][] computers) {
        answer = n;
        
        parent = new int[n]; // 자신(idx)의 부모를 담는 배열 설정
        
        for(int i = 0; i < n; i++) {
            parent[i] = i;  // 현재 부모는 자기 자긴(idx)
        }
        
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        return answer;
    }
    
    void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb) {  // 사이클 검사, 이미 같은 그룹인데 또 그룹으로 넣으려고 할 때
            return;
        }
        
        parent[rb] = ra;
        answer--;   // 그룹으로 합쳐지면 -1
    }
    
    int find(int x) {
        if(parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        
        return parent[x];
    }
}