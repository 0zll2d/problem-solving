import java.util.*;

public class Solution {
    public int[] solution(int []arr) {        
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(arr[0]);
        
        for(int i = 1; i < arr.length; i++) {
            if(dq.peekLast() == arr[i]) {
                continue;
            }    
            dq.offer(arr[i]);
        }
        
        return dq.stream().mapToInt(Integer::intValue).toArray();
    }
}

/**
 * Deque (Double Ended Queue) 주요 메서드 정리
 *
 * [삽입]
 * - offer(E e)       : offerLast(E e) 와 동일
 * - offerFirst(E e)  : 덱의 앞에 요소 삽입
 * - offerLast(E e)   : 덱의 뒤에 요소 삽입
 *
 * [삭제]
 * - poll()           : pollFirst() 와 동일
 * - pollFirst()      : 덱의 앞 요소 제거 후 반환
 * - pollLast()       : 덱의 뒤 요소 제거 후 반환
 *
 * [조회]
 * - peek()           : peekFirst() 와 동일
 * - peekFirst()      : 덱의 앞 요소 조회
 * - peekLast()       : 덱의 뒤 요소 조회
 */
