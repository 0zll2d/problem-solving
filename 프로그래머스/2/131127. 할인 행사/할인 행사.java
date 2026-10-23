import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        Map<String, Integer> m = new HashMap<>();   // 원하는 품목
        
        for(int i = 0; i < want.length; i++) {
            m.put(want[i], number[i]);
        }
        
        Map<String, Integer> items = new HashMap<>();    // 10일간 할인하는 품목 확인
        
        for(int i = 0; i < 10; i++) {
            items.put(discount[i], items.getOrDefault(discount[i], 0) + 1);
        }
        
        // 10일 고정 - 슬라이딩 윈도우    // [start, end)
        int start = 0;
        int end = 10;
        
         while(end <= discount.length) {
             // 원하는 제품과 수량이 맞는지 확인
             boolean joined = true;
             
             for(String w : want) {
                 if(!items.containsKey(w) || m.get(w) > items.get(w)) {
                    joined = false;
                    break;
                 }
             }
             
             if(joined) {
                 System.out.println(start);
                 answer++;
             }
             
             if(end == discount.length) {
                 break;
             }
             
             // 시작 + 1 - Map에서 1개 제거
             items.put(discount[start], items.get(discount[start]) - 1);
             start++;
             
             // 끝 + 1 - Map에서 1개 추가
             items.put(discount[end], items.getOrDefault(discount[end], 0) + 1);    // NULL 주의, 맵에 값이 없는데 get 시 NULL 예외 발생
             end++;
         }
        
        return answer;
    }
}