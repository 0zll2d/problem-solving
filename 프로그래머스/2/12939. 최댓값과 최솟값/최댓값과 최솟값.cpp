#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

string solution(string s) {
    stringstream ss(s); // 문자열 공백으로 자르기
    vector<int> nums;
    int num;
    
    while(ss >> num) {     // string -> int 바로 받기
        nums.push_back(num);
    }
    
    sort(nums.begin(), nums.end());
    
    return to_string(nums.front()) + " " + to_string(nums.back());   // 정수 -> 문자열
}