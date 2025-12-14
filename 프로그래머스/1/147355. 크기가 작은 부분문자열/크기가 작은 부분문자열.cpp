#include <string>
#include <vector>

using namespace std;

int solution(string t, string p) {
    int answer = 0;
    
    for(int i = 0; i <= t.size() - p.size(); i++) {
        if(t[i] > p[0]) {
            continue;
        }
        
        string t_sub = t.substr(i, p.size());
        if(t_sub <= p) {
            answer++;
        }
    }
    
    return answer;
}

