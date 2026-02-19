#include <string>
#include <algorithm>

using namespace std;

long long solution(long long n) {
    // long long 에서 string 변환: to_string()
    string s = to_string(n);
    
    sort(s.rbegin(), s.rend());
    
    // string 에서  long long 변환: stoll()
    return stoll(s);
}