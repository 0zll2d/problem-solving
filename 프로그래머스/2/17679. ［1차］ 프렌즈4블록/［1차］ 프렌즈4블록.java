import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        char[][] A = new char[m][n];
        
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                A[i][j] = board[i].charAt(j);
            }
        }
        
        while(true) {
            boolean getScore = false;
            boolean[][] erase = new boolean[m][n];

            for(int i = 0; i < m - 1; i++) {
                for(int j = 0; j < n - 1; j++) {
                    if(A[i][j] == '-') {
                        continue;
                    }
                    else if(A[i][j] == A[i + 1][j] && A[i][j] == A[i][j + 1] && A[i][j] == A[i + 1][j + 1]) {
                        erase[i][j] = erase[i + 1][j] = erase[i][j + 1] = erase[i + 1][j + 1] = true;
                        getScore = true;
                    }
                }
            }

            if(!getScore) {
                break;
            }

            List<List<Character>> list = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                list.add(new ArrayList<>());
            }

            for(int j = 0; j < n; j++) {
                int none = 0;

                for(int i = m - 1; i >= 0; i--) {
                    if(erase[i][j]) {
                        none++;
                    } else {
                        list.get(j).add(A[i][j]);
                    }
                }

                for(int i = 0; i < none; i++) {
                    list.get(j).add('-');
                }

                answer += none;
            }

            for(int j = 0; j < n; j++) {
                for(int i = 0; i < m; i++) {
                    A[i][j] = list.get(j).get(m - i - 1);
                }
            }
        }
        
        return answer;
    }
}