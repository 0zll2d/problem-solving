#include <string>
#include <vector>
#include <map>

using namespace std;

vector<int> solution(vector<string> name, vector<int> yearning, vector<vector<string>> photo) {
    vector<int> answer;   
    map<string, int> m;
    
    for(int i = 0; i < name.size(); i++) {
        m.insert({name[i], yearning[i]});
    }
    
    for(vector<string> p : photo) {
        int score = 0;
        for(int i = 0; i < p.size(); i++) {
            // key 없으면 자동 생성, value는 0
            if(m[p[i]]) {
                score += m[p[i]];
            }
        }
        answer.push_back(score);
    }
    
    return answer;
}
