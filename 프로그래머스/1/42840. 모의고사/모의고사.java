import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = new int[4];
        int max = 0;

        score[1] = grade(answers, person1);
        max = Math.max(max, score[1]);

        score[2] = grade(answers, person2);
        max = Math.max(max, score[2]);

        score[3] = grade(answers, person3);
        max = Math.max(max, score[3]);

        List<Integer> answer = new ArrayList<>();

        for(int i = 1; i <= 3; i++) {
            if(max == score[i]) {
                answer.add(i);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private int grade(int[] answer, int[] person) {
        int correct = 0;

        for(int i = 0; i < answer.length; i++) {
            if(answer[i] == person[i % person.length]) {
                correct++;
            }
        }

        return correct;
    }
}