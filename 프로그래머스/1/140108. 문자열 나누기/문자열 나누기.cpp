#include <string>
#include <vector>

using namespace std;

int solution(string s) {
    int answer = 0;
    
    char x = s[0];
    int same = 0;
    int diff = 0;
    
    for(int i = 0; i < s.size(); i++) {
        if(x == s[i]) {
            same++;
        } else {
            diff++;
        }
        
        // 두 횟수가 같은 경우 문자열 분리
        if(same == diff) {
            answer++;
            
            x = s[i + 1];
            same = 0;
            diff = 0;
        }
    }
    
    // 두 횟수가 다른 상태에서 더 이상 읽을 글자가 없으면 문자열 분리(+1)
    if(same != diff) {
        answer++;
    }
        
    return answer;
}