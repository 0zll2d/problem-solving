import java.util.*;

class Solution {
    Map<Character, Character> bracket;

    boolean check(Deque<Character> q) {
        Deque<Character> stk = new ArrayDeque<>();

        while(!q.isEmpty()) {
            char c = q.poll();

            if(c == '(' || c == '{' || c == '[') {
                stk.push(c);
            } else {
                if(stk.isEmpty() || bracket.get(c) != stk.peek()) {
                    return false;
                }
                stk.pop();
            }
        }

        return stk.isEmpty();
    }

    public int solution(String s) {
        int answer = 0;

        bracket = new HashMap<>();
        bracket.put(')', '(');
        bracket.put(']', '[');
        bracket.put('}', '{');

        Deque<Character> q = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            q.offer(c);
        }

        for(int i = 0; i < s.length(); i++) {
            // 1. 괄호 회전
            q.offer(q.poll());

            // 2. 올바른 괄호인지 검사
            if(check(new ArrayDeque<>(q))) {    // check(q)는 값 복사가 아닌 참조값 복사
                answer++;
            }
        }

        return answer;
    }
}