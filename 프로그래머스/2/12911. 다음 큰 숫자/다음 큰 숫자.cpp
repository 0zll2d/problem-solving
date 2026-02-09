#include <string>
#include <vector>
#include <iostream>

using namespace std;

// 2진수 변환 시 1의 개수
int count_one(int n) {
    int cnt = 0;
    
    while(n) {
        if(n % 2 == 1) {
            cnt++;
        }
        n /= 2;
    }
    
    return cnt;
}

int solution(int n) {
    int answer = 0;
    
    int one = count_one(n);
    
    while(true) {
        n++;
        
        int next = count_one(n);
        
        if(one == next) {
            answer = n;
            break;
        }
    }
    
    return answer;
}
