class Solution {
    public int lastStoneWeight(int[] stones) {
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);  // [PriorityQueue] Java는 최소힙, C++은 최대힙

        for(int stone : stones) {
            pq.offer(stone);    // [PriorityQueue] 삽입: Java는 offer, C++은 push
        }

        while(pq.size() >  1) {
            int y = pq.poll();  // [PriorityQueue] 삭제: Java는 poll, C++은 pop
            int x = pq.poll();

            if(x != y) {
                pq.offer(y - x);
            }
        }
        
        return pq.isEmpty() ? 0 : pq.peek();    // [PriorityQueue] 우선순위 값: Java는 peek, C++은 top
    }
}