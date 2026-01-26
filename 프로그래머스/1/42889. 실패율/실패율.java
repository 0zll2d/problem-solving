import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];

        // stage별 도전 중인 사용자 수 (1 ~ N + 1)
        int[] challenge = new int[N + 2];
        for (int stage : stages) {
            challenge[stage]++;
        }

        // 각 stage의 실패율 계산
        List<Failure> list = new ArrayList<>();
        int rest = stages.length;

        for (int stage = 1; stage <= N; stage++) {
            // 현재 stage에 도전한 사람 중 실패한 비율
            double fail = (rest == 0) ? 0.0 : (double) challenge[stage] / rest;
            list.add(new Failure(stage, fail));
            rest -= challenge[stage];
        }

        // 실패율 내림차순 정렬
        // stable sort이므로 실패율이 같은 경우
        // list에 추가된 순서(= stage 오름차순)가 유지된다
        Collections.sort(list, (a, b) -> Double.compare(b.fail, a.fail));

        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).stage;
        }

        return answer;
    }
    
    class Failure {
        int stage;
        double fail;

        Failure(int stage, double fail) {
            this.stage = stage;
            this.fail = fail;
        }
    }
}