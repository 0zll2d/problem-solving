#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    
    // 소수 판별 알고리즘 참고 (12977. 소수 만들기)
    for(int i = 1; i * i <= n; i++) {
        if(n % i == 0) {
            int j = n / i;
            
            // i와 j는 약수의 쌍
            answer += i + j;
            
            // 예외 처리 (예: n = 36)
            if(i == j) {
                answer -= i;
            }
        }
    }
    
    return answer;
}