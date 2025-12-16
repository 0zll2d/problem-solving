import java.util.*;

public class Solution {
    public int solution(int n) {
        int answer = 0;

        while(n > 0) {
            answer += n % 10;
            n /= 10;
        }

        return answer;
    }
}

// Java에서 while(n)이 오류 나는 이유
// 오류 메세지: int cannot be converted to boolean
// 이유: 조건식은 반드시 boolean 타입만 허용함, Java는 int → boolean 자동 변환을 허용하지 않음

// C++에서는 while(n)이 오류 나지 않은 이유
// 이유: C++에서는 int → boolean 암묵적 변환을 허용함(0은 false)