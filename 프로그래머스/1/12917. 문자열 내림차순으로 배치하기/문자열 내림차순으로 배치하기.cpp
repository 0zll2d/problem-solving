#include <string>
#include <algorithm>    // sort 헤더

using namespace std;

string solution(string s) {
    sort(s.rbegin(), s.rend());
    // sort(s.begin(), s.end(), greater<char>());
    return s;
}

// void sort(RandomIt first, RandomIt last, Compare comp)