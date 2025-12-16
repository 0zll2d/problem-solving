class Solution {
    public double solution(int[] arr) {
        double answer = 0;
        
        for(int num : arr) {
            answer += num;    
        }
        
        return answer / arr.length;
    }
}

// Java에서 배열의 크기는 length 필드 사용
// 배열 생성 시 크기가 고정되어 변하지 않기 때문에, 크기를 "계산"할 필요가 없어 속성 값으로 보관