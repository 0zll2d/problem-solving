import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> m = new HashMap<>();
        
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            answer[i] = i - m.getOrDefault(c, i + 1);       // 존재하지 않으면, i - (i + 1) = -1 반환을 위해 사용
            m.put(c, i);
        }
        
        return answer;
    }
}

/**
 * ============================
 * Java Map 정리 (코딩테스트 기준)
 * ============================
 *
 * [주요 Map 구현체]
 *
 * 1. HashMap<K, V>
 *    - 평균 시간복잡도 O(1)
 *    - 순서 보장 X
 *    - 코딩테스트에서 가장 많이 사용
 *
 * 2. LinkedHashMap<K, V>
 *    - 삽입 순서 유지
 *    - 순서가 필요한 문제에서 사용
 *
 * 3. TreeMap<K, V>
 *    - Key 기준 자동 정렬
 *    - 시간복잡도 O(log N)
 *    - 최솟값 / 최댓값 조회에 유리
 *
 * [자주 사용하는 Map 메서드]
 *
 * 1. V put(K key, V value)
 *    - key에 value 저장
 *
 * 2. V get(Object key)
 *    - key가 없으면 null 반환
 *
 * 3. V getOrDefault(K key, V defaultValue)
 *    - key가 없으면 defaultValue 반환
 *    - NullPointerException 방지에 매우 중요
 *
 * 4. boolean containsKey(Object key)
 *    - key 존재 여부 확인
 *
 * 5. V remove(Object key)
 *    - key 삭제
 *    - key가 없으면 null 반환
 *
 * 6. Set<Map.Entry<K, V>> entrySet()
 *    - Map 순회 시 key와 value를 동시에 사용할 때
 *
 * 7. Set<K> keySet()
 *    - key만 순회하며 value는 get(key)로 조회할 때
 */