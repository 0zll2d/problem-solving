import java.util.*;

class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) {
            return new int[]{ -1 };
        }
        
        int min = Arrays.stream(arr).min().getAsInt();
        
        return Arrays.stream(arr).filter(num -> num != min).toArray();
    }
}

// [Java에서 가장 작은 값 구하는 방법]

// 1. Collection(List, Set 등)
//    - Collections.min(collection)
//    - 내부적으로 Comparable / Comparator 기반 비교
//    - 값이 없으면 예외(NoSuchElementException) 발생
//
//    예)
//    int min = Collections.min(list);


// 2. primitive 배열 (int[], long[], double[])
//    - Arrays.stream(배열).min()
//
//    ⚠️ min()의 반환 타입은 값 자체가 아니라 Optional 계열
//       int[]    → OptionalInt
//       long[]   → OptionalLong
//       double[] → OptionalDouble
//
//    그래서 실제 값을 꺼내기 위해 getAsInt() 필요
//
//    예)
//    int min = Arrays.stream(arr).min().getAsInt();


// [왜 Arrays.stream().min()은 getAsInt()가 필요할까?]
//
// - 배열이 비어 있을 가능성이 있기 때문
// - 최소값이 "존재하지 않는 상태"를 표현하기 위해 Optional 반환
//
// 즉,
//   min()            → OptionalInt
//   getAsInt()       → OptionalInt → int
//
// 값이 없는데 getAsInt()를 호출하면
//   → NoSuchElementException 발생


// [안전한 대안]
// 값이 없을 경우 기본값을 지정하고 싶다면 orElse() 사용
//
// int min = Arrays.stream(arr)
//                 .min()
//                 .orElse(Integer.MAX_VALUE);


// [그럼 왜 Collections.min()은 Optional을 안 쓸까?]
//
// - Collections.min()은
//   "비어 있으면 예외를 던진다"는 계약을 가진 메서드
// - Optional을 사용하지 않고 즉시 실패(fail-fast)하는 방식
//
// 반면 Stream API는
// - 중간 연산 / 파이프라인 특성상
// - 결과가 없을 수도 있음을 타입으로 명확히 표현해야 해서 Optional 사용