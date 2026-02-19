#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;
    
    for(vector<int> &cmd : commands) {
        int i = cmd[0] - 1;
        int j = cmd[1];
        int k = cmd[2] - 1;
        
        // 부분 벡터 만들기 = 새 벡터 만들어 구간 자르기
        vector<int> sub(array.begin() + i, array.begin() + j);
        sort(sub.begin(), sub.end());
        answer.push_back(sub[k]);
    }
    
    return answer;
}

