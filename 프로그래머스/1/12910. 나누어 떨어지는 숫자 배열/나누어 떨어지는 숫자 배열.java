import java.util.*;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        List<Integer> answer = new ArrayList<>();
        
        for(int num : arr) {
            if(num % divisor == 0) {
                answer.add(num);
            }    
        }
        
        if(answer.size() == 0) {
            answer.add(-1);
        } else {
            Collections.sort(answer);  
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}


/*
====================================================
[List → 배열 변환 방식 정리]
====================================================

1️⃣ List<String> → String[] (참조형 배열)

- 방식 ①
  list.toArray(new String[0]);

  · 전달된 배열은 "타입 정보(String[])" 제공용
  · 배열 길이가 부족하므로 내부에서
    new String[list.size()] 를 다시 생성
  · 리플렉션(Array.newInstance) 사용
    · 리플렉션: 실행 중에 타입 정보를 기반으로 객체를 생성하는 기술

- 방식 ② (Java 11+)
  list.toArray(String[]::new);

  · size -> new String[size]
  · 배열 생성 함수를 직접 전달
  · 타입과 길이가 처음부터 정확히 설정됨
  · 가장 깔끔하고 권장되는 방식


2️⃣ List<Integer> → Integer[] (Wrapper 배열)

- 방식
  list.toArray(new Integer[0]);

  · 참조형 → 참조형 변환
  · 박싱/언박싱 없음
  · 내부에서 Integer[] 생성


3️⃣ List<Integer> → int[] (기본형 배열)

❌ toArray() 사용 불가
→ 제네릭은 기본형(int)을 지원하지 않음
→ 반드시 "언박싱" 과정 필요


3-1️⃣ for-loop + get() 방식

  int[] result = new int[list.size()];
  for (int i = 0; i < list.size(); i++) {
      result[i] = list.get(i); // Integer → int (auto-unboxing)
  }

  · 가장 빠름
  · 코딩 테스트에서 권장
  · List는 배열이 아니므로 요소 접근은 get() 사용


3-2️⃣ stream() + mapToInt() 방식

  list.stream()
      .mapToInt(Integer::intValue)
      .toArray();

  · stream() : List를 순차 처리용 데이터 흐름으로 변환
  · mapToInt() : Integer → int 언박싱
  · toArray() : int[] 생성
  · 가독성은 좋으나 for-loop보다 성능은 떨어짐


4️⃣ 왜 list.get()을 사용하는가?

- List는 배열이 아님
- 내부 구현(ArrayList / LinkedList)을 숨기기 위해
- 요소 접근을 메서드(get)로 추상화
- list[i] 같은 접근은 문법적으로 불가능


5️⃣ 박싱 vs. 언박싱

  박싱(Boxing): 기본형 → 참조형
  
  int a = 10;
  Integer b = a;
  
  언박싱(Unboxing): 참조형 → 기본형
  
  Integer b = 10;
  int a = b;
  
====================================================
*/