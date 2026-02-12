#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int answer = 0;
    
    priority_queue<int, vector<int>, greater<int>> pq;
    
    for(int scov : scoville) {
        pq.push(scov);
    }
    
    while(pq.size() > 1 && pq.top() < K) {
        int food1 = pq.top();
        pq.pop();
        
        int food2 = pq.top();
        pq.pop();
        
        pq.push(food1 + food2 * 2);
        answer++;
    }
    
    if(pq.top() < K) {
        answer = -1;
    }
    
    return answer;
}

// C++에서 PQ는 기본적으로 최대힙 (Java는 최소힙)