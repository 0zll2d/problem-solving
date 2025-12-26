import java.util.*;

class Solution {
    public String solution(String[] seoul) {
        int x = Arrays.asList(seoul).indexOf("Kim");
        return "김서방은 " + x + "에 있다";
    }
}

/*
====================================================
[Java에서 배열 → List 변환]
====================================================

1. 참조형 배열 (T[] → List<T>)

- 기본 변환
  Arrays.asList(arr)

  · 배열을 List로 감싼 view 형태
  · 크기 고정 (add / remove 불가)
  · 배열과 List가 같은 데이터를 공유

- 수정 가능한 List가 필요할 경우
  new ArrayList<>(Arrays.asList(arr))


2. 기본형 배열 (int[] → List<Integer>)

- Arrays.asList(int[]) 사용 불가
  · int[] 전체가 하나의 요소로 들어감

- for-loop 방식
  List<Integer> list = new ArrayList<>();
  for (int n : arr) {
      list.add(n); // auto-boxing
  }

- stream 방식
  · Java 16+
    Arrays.stream(arr).boxed().toList();

  · Java 8 ~ 15
    Arrays.stream(arr)
          .boxed()
          .collect(Collectors.toList());


====================================================
[Java에서 값 찾기]
====================================================

1. 배열(Array)

- 배열에는 indexOf() 메서드가 없음
- 값 탐색은 반복문 사용

  for (int i = 0; i < arr.length; i++) {
      if (조건) {
          // 찾은 인덱스 i
          break;
      }
  }

- 참조형 배열의 경우
  Arrays.asList(arr).indexOf(value) 사용 가능


2. List(Collection)

- indexOf(value)
  · 값이 존재하면 인덱스 반환
  · 없으면 -1 반환
  · equals() 기준 비교

- contains(value)
  · 값 존재 여부만 확인 (boolean)

====================================================
*/