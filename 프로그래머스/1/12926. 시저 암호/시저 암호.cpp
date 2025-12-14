#include <string>
#include <cctype> // isupper, islower 헤더

using namespace std;

string solution(string s, int n) {
    string answer = "";
    
    for(char c : s) {
        if(isupper(c)) {
            c = 'A' + (c - 'A' + n) % 26;
        } else if(islower(c)) {
            c = 'a' + (c - 'a' + n) % 26;
        }
        answer += c;
    }
    
    return answer;
}
    