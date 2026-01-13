import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        int[] answer = new int[score.length];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < score.length; i++) {
            if(pq.size() < k) {
                pq.offer(score[i]);
            } else {
                if(score[i] >= pq.peek()) {
                    pq.poll();
                    pq.offer(score[i]);
                }
            }
            answer[i] = pq.peek();
        }

        return answer;
    }
}

/**
 * PriorityQueue (Java) - 코딩테스트 핵심 메서드 정리
 *
 * [개요]
 * - PriorityQueue는 힙(Heap) 기반 자료구조
 * - 기본 동작은 최소 힙(Min-Heap)
 * - Comparator를 사용하면 최대 힙(Max-Heap) 구현 가능
 *
 * [시간 복잡도]
 * - 삽입(offer): O(log N)
 * - 삭제(poll): O(log N)
 * - 조회(peek): O(1)
 *
 * --------------------------------------------------
 * 1. 최소 힙 (Min-Heap)
 * --------------------------------------------------
 *
 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
 *
 * - 가장 작은 값이 우선순위가 높음
 * - peek(), poll() 시 최솟값 반환
 *
 * [코딩테스트 핵심 메서드]
 *
 * offer(E e)
 *  - 원소 삽입
 *  - 힙 구조를 유지하며 삽입됨
 *
 * poll()
 *  - 우선순위가 가장 높은 원소 제거 후 반환
 *  - 최소 힙에서는 "가장 작은 값"
 *  - 큐가 비어있으면 null 반환
 *
 * peek()
 *  - 우선순위가 가장 높은 원소 조회
 *  - 제거하지 않음
 *  - 큐가 비어있으면 null 반환
 *
 * size()
 *  - 현재 큐에 들어 있는 원소 개수 반환
 *
 * isEmpty()
 *  - 큐가 비어 있는지 여부 확인
 *
 * --------------------------------------------------
 * 2. 최대 힙 (Max-Heap)
 * --------------------------------------------------
 *
 * PriorityQueue<Integer> maxHeap =
 *     new PriorityQueue<>(Collections.reverseOrder());
 *
 * - 가장 큰 값이 우선순위가 높음
 * - peek(), poll() 시 최댓값 반환
 *
 * [동작 메서드]
 * - offer(), poll(), peek(), size(), isEmpty()
 * - 사용법 및 시간 복잡도는 최소 힙과 동일
 *
 * --------------------------------------------------
 * 3. 크기 제한 패턴 (Top-K 문제 필수)
 * --------------------------------------------------
 *
 * PriorityQueue<Integer> pq = new PriorityQueue<>();
 * int K = 3;
 *
 * for (int x : arr) {
 *     pq.offer(x);
 *     if (pq.size() > K) {
 *         pq.poll(); // 가장 작은 값 제거
 *     }
 * }
 *
 * - 큐의 크기를 논리적으로 K로 유지
 * - 상위 K개 값 유지에 사용
 *
 * --------------------------------------------------
 * 4. 주의사항 (코딩테스트 단골 함정)
 * --------------------------------------------------
 *
 * - new PriorityQueue<>(K)는 "크기 제한"이 아님
 *   → 초기 용량(capacity) 설정일 뿐
 *
 * - PriorityQueue는 정렬된 상태로 저장되지 않음
 *   → 전체 순회 결과는 정렬되어 있지 않음(최대값 혹은 최소값만 판별)
 */