#include <vector>
#include <iostream>

using namespace std;

bool isPrime(int sum) {
    for(int i = 2; i * i <= sum; i++) {
        if(sum % i == 0) {
            return false;
        }
    }
    return true;
}

int solution(vector<int> nums) {
    int answer = 0;

    for(int i = 0; i < nums.size(); i++) {
        for(int j = i + 1; j < nums.size(); j++) {
            for(int k = j + 1; k < nums.size(); k++) {
                if(isPrime(nums[i] + nums[j] + nums[k])) {
                    answer++;
                }
            }
        }
    }

    return answer;
}

// [ 소수 판별 알고리즘 ]

// 1. 소수인지 확인하기 위해 약수 존재 여부를 검사한다.
//    - 1과 자기 자신을 제외한 약수가 하나라도 있으면 소수가 아니다.

// 2. 약수 검사는 n의 제곱근(√n)까지만 하면 된다.
//    - 모든 약수는 짝이 있으므로, 한 쪽은 반드시 √n 이하에 존재하기 때문.

// 예) n = 36
//    1 * 36
//    2 * 18
//    3 * 12
//    4 * 9
//    6 * 6
//    → 작은 약수들은 모두 √36 = 6 이하이므로
//      2 ~ 6까지만 검사해도 소수가 아님을 알 수 있다.


        