#include <string>
#include <vector>

using namespace std;

bool isEven(int num) {
    int cnt = 0;
    
    // i * i <= num 을 사용하는 이유:
    //   - i*i == num (즉 i = √num)인 경우에도 i는 num의 약수이므로 반드시 검사해야 함
    //   - i*i < num 으로 작성하면 √num을 놓쳐 제곱수의 약수를 제대로 계산할 수 없음
    //   - 소수 판별 알고리즘 참고 (12977. 소수 만들기)
    for(int i = 1; i * i <= num; i++) {
        if(num % i == 0) {
            int j = num / i;
            
            cnt += 2;
            
            if(i == j) {
                cnt--;
            }
        }
    }
    
    return cnt % 2 == 0;
}

int solution(int left, int right) {
    int answer = 0;
    
    for(int i = left; i <= right; i++) {
        if(isEven(i)) {
            answer += i;
        } else {
            answer -= i;
        }
    }
    
    return answer;
}