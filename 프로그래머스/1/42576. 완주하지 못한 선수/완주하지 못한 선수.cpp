#include <string>
#include <vector>
#include <map>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    
    map<string, int> marathon;
    
    for(string p : participant) {
        marathon[p]++;
    }
    
    for(string c: completion) {
        marathon[c]--;
    }

    for(const auto& [key, value] : marathon) {
        if(value > 0) {
            answer = key;
            break;
        }
    }
    
    return answer;
}