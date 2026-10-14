class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        
        Map<Integer, Integer> m = new HashMap<>();

        for(int num : nums) {
            m.put(num, m.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);   // [PriorityQueue] Java는 최소힙, C++은 최대힙

        for(int key : m.keySet()) {
            pq.offer(new int[]{key, m.get(key)});   // [PriorityQueue] 삽입 Java는 offer, C++은 push
        }

        int[] answer = new int[k];

        for(int i = 0; i < k; i++) {
            answer[i] = pq.poll()[0];   // [PriorityQueue] 값 제거 Java는 poll, C++은 pop
        }

        return answer;
    }
}