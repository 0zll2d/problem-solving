#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> solution(vector<string> strings, int n) {

    sort(strings.begin(), strings.end(),
        // [n] : 캡처 목록
        // - 람다는 외부 변수를 직접 사용할 수 없으므로 기준값 n을 캡처
        // - 값 캡처를 사용해 정렬 도중 기준값이 변하지 않도록 보장
        [n](const auto& a, const auto& b) {
            // auto :
            // - 컴파일 시점에 타입을 자동 추론
            // - 여기서는 strings의 요소 타입(string)으로 추론됨
            // const auto& :
            // - 문자열 복사를 방지하기 위해 참조 사용
            // - 비교만 수행하므로 const로 수정 방지
            if (a[n] == b[n]) {
                return a < b;      // n번째 문자가 같으면 사전순
            }
            return a[n] < b[n];    // n번째 문자 기준 오름차순
        }
    );

    return strings;
}

// [커스텀 정렬 정리]
// - [] : 람다 캡처 목록 (외부 변수 n 사용)
// - 값 캡처 : 정렬 기준의 안정성 보장
// - const auto& : 복사 비용 제거 + 읽기 전용 비교