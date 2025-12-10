#include <vector>
#include <algorithm>    // min 헤더파일
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) {
    vector<int> answer;
    int min_num = arr[0];
    
    for(int i = 1; i < arr.size(); i++) {
        min_num = min(min_num, arr[i]);
    }
    
    for(int i = 0; i < arr.size(); i++) {
        if(arr[i] == min_num) {
            continue;
        }
        answer.push_back(arr[i]);
    }
    
    if(answer.size() == 0) {
        answer.push_back(-1);
    }
    
    return answer;
}