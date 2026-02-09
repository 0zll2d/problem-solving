#include <string>
#include <vector>
#include <regex>    // 정규식
#include <algorithm>

using namespace std;

vector<int> solution(string x) {
    vector<int> answer = {0, 0};
    
    while(x.size() > 1) {
        // 1. x에서 0 제거
        string tmp = regex_replace(x, regex("0"), "");
        
        // 2. 0 제거 후 길이와 제거할 0의 개수 파악
        int rm = x.size() - tmp.size(); // 제거할 0의 개수
        int c = x.size() - rm;
        answer[1] += rm;
        
        // 3. c를 2진법 문자열로 변경
        x = "";
        while(c) {
            x += '0' + (c % 2);
            c /= 2;
        } 
        reverse(x.begin(), x.end());    // 문자열 뒤집기
        
        answer[0]++;
        
    }

    return answer;
}