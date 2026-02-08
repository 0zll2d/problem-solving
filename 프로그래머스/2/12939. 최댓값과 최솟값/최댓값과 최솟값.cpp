#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

string solution(string s) {
    stringstream ss(s); // 문자열 공백으로 자르기
    vector<int> nums;
    string snum;
    
    while(ss >> snum) {
        nums.push_back(stoi(snum));
    }
    
    sort(nums.begin(), nums.end());
    
    return to_string(nums[0]) + " " + to_string(nums[nums.size() - 1]);   // 정수 -> 문자열
}