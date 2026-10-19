class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        int[] answer = new int[temperatures.length];

        Deque<Integer> stk = new ArrayDeque<>();    // temperatures의 인덱스 저장

        for(int i = 0; i < temperatures.length; i++) {
            while(!stk.isEmpty() && temperatures[stk.peek()] < temperatures[i]) {
                answer[stk.peek()] = i - stk.peek();
                stk.pop();
            }
            stk.push(i);
        }

        return answer;
    }
}  
