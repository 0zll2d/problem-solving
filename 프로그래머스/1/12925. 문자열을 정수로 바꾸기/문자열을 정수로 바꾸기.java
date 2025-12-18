class Solution {
    public int solution(String s) {
        return Integer.parseInt(s);
    }
}

// String → 기본형(primitive)
// 1) int: Integer.parseInt()
// 2) long: Long.parseLong()
// 3) double: Double.parseDouble()

// 기본형 → String
// 1) String.valueOf()
// 2) 타입.toString()
// * String.valueOf(obj) 에서 obj가 null이면 "null" 반환
// * obj.toString() 에서 obj가 null이면 NPE

// String → 래퍼 클래스(wrapper)
// 1) Integer: Integer.valueOf()
// 2) Long: Long.valueOf()
// 3) Double: Double.valueOf()