#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(long long n) {
    long long answer = 0;
    
    vector<int> nums;
    
    while(n) {
        nums.push_back(n % 10);
        n /= 10;
    }
    
    sort(nums.rbegin(), nums.rend());
    
    for(int i = 0; i < nums.size(); i++) {
        answer = answer * 10 + nums[i];
    }
    
    return answer;
}