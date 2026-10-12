class Solution {
public:
    vector<vector<string>> groupAnagrams(vector<string>& strs) {

        map<string, vector<string>> group;

        for(int i = 0; i < strs.size(); i++) {
            string sorted_str = strs[i];
            sort(sorted_str.begin(), sorted_str.end());

            if(group.find(sorted_str) != group.end()) {     // 키 존재
                group[sorted_str].push_back(strs[i]);
            } else {    // 키 존재하지 않음
                group.insert({sorted_str, {strs[i]}});
            }
        }
        
        vector<vector<string>> answer;

        for(const auto& entry : group) {
            answer.push_back(entry.second);
        }

        return answer;
    }
};