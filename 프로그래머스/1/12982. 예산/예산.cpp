#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> d, int budget) {
    int answer = -1;
    
    sort(d.begin(), d.end());
    
    for(int i = 0; i < d.size(); i++) {
        if(budget - d[i] < 0) {
            answer = i;
            break;
        }
        budget -= d[i];
    }
    
    if(answer == -1) {
        answer = d.size();
    }
    
    return answer;
}