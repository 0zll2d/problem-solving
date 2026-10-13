class Solution {
    public boolean isAnagram(String s, String t) {
        
        char[] a = s.toCharArray();
        char[] b = t.toCharArray();

        // 정렬
        Arrays.sort(a);
        Arrays.sort(b);

        return Arrays.equals(a, b);    // 반복문으로 비교하지 않고 그대로 Array 객체 값 비교
    }
}


// [Array] 객체 비교
// int[] a = { 1, 2, 3 };
// int[] b = { 1, 2, 3 };
// int[] c = a;

// 참조 비교 (동일한 배열 객체인지 = 주소값 비교)
// System.out.println(a.equals(b));         // false
// System.out.println(a.equals(c));         // true

// 값 비교 (배열 길이와 각 인덱스의 값이 동일한지)
// System.out.println(Arrays.equals(a, b)); // true
// System.out.println(Arrays.equals(a, c)); // true