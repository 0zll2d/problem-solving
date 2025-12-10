#include <vector>
#include <set>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> numbers) {
    vector<int> answer;
    set<int> st;   // 정렬 + 중복 제거가 자동으로 이루어지는 자료구조 (Red-Black Tree 기반)

    // 모든 서로 다른 두 수의 합을 set에 삽입
    for (int i = 0; i < numbers.size() - 1; i++) {
        for (int j = i + 1; j < numbers.size(); j++) {
            st.insert(numbers[i] + numbers[j]);  
            // 시간복잡도: O(logM)  (M = set의 현재 원소 개수)
        }
    }

    // set → vector 변환 (정렬 필요 없음: set 자체가 오름차순 유지)
    answer.assign(st.begin(), st.end());  
    // 시간복잡도: O(M)

    return answer;
}

// 풀이 방법:
// 1. 모든 서로 다른 두 수의 조합을 만들고 그 합을 계산한다.
// 2. set에 삽입하여 자동으로 중복 제거 + 오름차순 정렬한다.
// 3. set의 값을 vector로 변환하여 반환한다.
//
// 주요 연산 시간복잡도:
// - 이중 for문: O(n^2)
// - set.insert(): O(log M)
// - set → vector 변환: O(M)
// 전체 시간복잡도: O(n^2 log n)
