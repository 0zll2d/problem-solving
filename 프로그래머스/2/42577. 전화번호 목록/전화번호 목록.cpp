#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    
    sort(phone_book.begin(), phone_book.end());
    
    for(int i = 0; i < phone_book.size() - 1; i++) {
        string p1 = phone_book[i];
        string p2 = phone_book[i + 1];
        
        if(p2.compare(0, p1.size(), p1) == 0) {
            return false;
        }
    }
    
    return true;
}

// 부분 문자열 비교
// s.compare(시작 위치, 길이, 비교할 문자열(t))
// s < t : 음수
// s == t : 0
// s > t : 양수

// 문자열 비교
// s.compare(t)

// 시간복잡도 O(길이)