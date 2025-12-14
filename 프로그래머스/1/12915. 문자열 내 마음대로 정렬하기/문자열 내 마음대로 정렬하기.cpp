#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int idx;

bool comp(const string& a, const string &b) {
    if(a[idx] == b[idx]) {
        return a < b;
    }
    return a[idx] < b[idx];
}

vector<string> solution(vector<string> strings, int n) {
    idx = n;

    sort(strings.begin(), strings.end(), comp);
    
    return strings;
}

// [커스텀 정렬 정리]
// 일반 Compare 함수 분리
// 일반 메서드의 매개 변수로 auto 사용 금지: 타입 추론이 불가능하기 때문