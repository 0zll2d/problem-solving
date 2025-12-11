#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> arr)  {
    vector<int> answer;
    queue<int> q;

    q.push(arr[0]);
    
    for(int i = 1; i < arr.size(); i++) {
        if(q.back() == arr[i]) {    // C++에서 Queue는 마지막 원소 참조할 수 있는 back() 제공 (JAVA는 제공 안 함)
            continue;
        }
        
        q.push(arr[i]);
    }
    
    while(!q.empty()) {
        answer.push_back(q.front());
        q.pop();
    }
    
    return answer;
}

