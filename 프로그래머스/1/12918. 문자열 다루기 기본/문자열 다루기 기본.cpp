#include <string>
#include <cctype>   // isdigit 헤더파일

using namespace std;

bool solution(string s) {
    bool answer = true;
    
    // 1. 문자열 길이 판별
    if(s.size() != 4 && s.size() != 6) {
        return false;
    }
    
    // 2. 숫자 문자열 판별
    for(char c : s) {
        if(!isdigit(c)) {
            return false;
        }
    }
    
    return answer;
}