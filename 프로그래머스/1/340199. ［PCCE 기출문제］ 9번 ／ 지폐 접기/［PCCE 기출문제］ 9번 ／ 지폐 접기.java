class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;

        while(true) {
            // wallet, bill 오름차순 정렬
            wallet = sort(wallet);
            bill = sort(bill);
            
            // 종료 조건
            if(wallet[0] >= bill[0] && wallet[1] >= bill[1]) {
                break;
            }
            
            // 지폐 긴 부분 반으로 접기
            bill[1] /= 2;

            answer++;
        }

        return answer;
    }
    
    private int[] sort(int[] arr) {
        if(arr[0] > arr[1]) {
            int tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
        
        return arr;
    }
}