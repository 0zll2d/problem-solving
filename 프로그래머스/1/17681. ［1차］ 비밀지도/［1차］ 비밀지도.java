class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        for(int i = 0; i < n; i++) {
            int res = arr1[i] | arr2[i];
            String binaryString = Integer.toBinaryString(res);  // 10진수 -> 2진수 문자열 변환 (특징: 앞에 0 안 붙음)

            // 1. 앞에 0 채우기
            if(binaryString.length() != n) {
                binaryString = "0".repeat(n - binaryString.length()) + binaryString;
            }

            // 2. 1 -> '#', 0 -> '0' 변환
            binaryString = binaryString.replaceAll("1", "#");
            binaryString = binaryString.replaceAll("0", " ");

            answer[i] = binaryString;
        }

        return answer;
    }
}

// [Java에서 진수 문제]
// 1️⃣ 10진수 → N진수 (문자열)
// Integer.toString(n, radix);

// 2️⃣ N진수 → 10진수
// Integer.parseInt(str, radix);

// 3️⃣ 비트 결과 확인 (문자열)
// Integer.toBinaryString(n);