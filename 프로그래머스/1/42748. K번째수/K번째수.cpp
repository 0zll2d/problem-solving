#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for(auto command : commands) {
        int i = command[0];
        int j = command[1];
        int k = command[2];
        
        vector<int> sub_array(array.begin() + i - 1, array.begin() + j);
        
        sort(sub_array.begin(), sub_array.end());
        
        answer.push_back(sub_array[k - 1]);
        
    }
    
    return answer;
}

// [부분 vector 구하기]
// 예: left = 1, right = 4;
// vector<int> sub(array.begin() + left, array.begin() + right);
// [left, right) 범위를 의미