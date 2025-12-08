#include <string>

using namespace std;

bool solution(string s) {
    bool answer = true;
    
    // 1. 문자열 길이 판별
    if(s.size() != 4 && s.size() != 6) {
        return false;
    }
    
    // 2. 숫자 문자열 판별
    for(char c : s) {
        // isdigit() 생각이 나지 않은 경우
        if(c < '0' || c > '9') {
            return false;
        }
    }
    
    return answer;
}