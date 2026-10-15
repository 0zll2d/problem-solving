class StockSpanner {

    private final Deque<int[]> stk; // {price, 자신보다 작은 개수}

    public StockSpanner() {
        this.stk = new ArrayDeque<>();
    }
    
    public int next(int price) {

        int count = 1;

        while(!stk.isEmpty() && stk.peek()[0] <= price) {
            count += stk.pop()[1];
        }

        stk.push(new int[]{price, count});

        return count;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */

