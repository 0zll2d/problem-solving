import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        Set<Integer> st = new HashSet<>();
           
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                st.add(numbers[i] + numbers[j]);
            }
        }
        
        return st.stream().sorted().mapToInt(Integer::intValue).toArray();
    }
}

/**
 * ============================
 * Java Set 정리 (코딩테스트 기준)
 * ============================
 *
 * [주요 Set 구현체]
 *
 * 1. HashSet<E>
 *    - 중복 허용 X
 *    - 순서 보장 X
 *    - 평균 시간복잡도 O(1)
 *    - 코딩테스트에서 가장 많이 사용
 *    - 중복 제거, 존재 여부 체크에 최적
 *
 * 2. LinkedHashSet<E>
 *    - 중복 허용 X
 *    - 삽입 순서 유지
 *    - 시간복잡도 O(1)
 *    - "중복 제거 + 입력 순서 유지"가 필요한 문제에서 사용
 *
 * 3. TreeSet<E>
 *    - 중복 허용 X
 *    - 자동 정렬 (기본 오름차순)
 *    - 시간복잡도 O(log N)
 *    - 최솟값 / 최댓값 / 범위 탐색 문제에 유리
 *
 * [자주 사용하는 Set 메서드]
 *
 * 1. boolean add(E e)
 *    - 요소 e 추가
 *    - 이미 존재하면 false 반환
 *
 * 2. boolean contains(Object o)
 *    - 요소 o 존재 여부 확인
 *    - 코딩테스트에서 매우 자주 사용
 *
 * 3. boolean remove(Object o)
 *    - 요소 o 삭제
 *    - 존재하지 않으면 false 반환
 *
 * 4. int size()
 *    - Set에 저장된 요소 개수 반환
 *
 * 5. boolean isEmpty()
 *    - Set이 비어 있는지 확인
 *
 * 6. void clear()
 *    - Set의 모든 요소 제거
 *
 * 7. Iterator<E> iterator()
 *    - Set 순회용 반복자 반환
 *
 * [Set 순회 방법]
 *
 * 1. 향상된 for문 (가장 일반적)
 *    for (E e : set) {
 *        // e 사용
 *    }
 *
 * 2. Iterator 사용
 *    Iterator<E> it = set.iterator();
 *    while (it.hasNext()) {
 *        E e = it.next();
 *    }
 *
 * [코딩테스트에서 자주 나오는 활용 패턴]
 *
 * 1. 중복 제거
 *    Set<Integer> set = new HashSet<>();
 *    for (int x : arr) {
 *        set.add(x);
 *    }
 *
 * 2. 빠른 존재 여부 체크
 *    if (set.contains(x)) {
 *        // 이미 등장한 값
 *    }
 *
 * 3. 정렬된 결과가 필요할 때
 *    Set<Integer> set = new TreeSet<>();
 *
 * [주의 사항]
 *
 * - Set은 인덱스 접근(get(i)) 불가
 * - 값 조회가 아니라 "존재 여부" 판단용 자료구조
 * - 중복을 허용해야 한다면 List 또는 Map 사용
 */