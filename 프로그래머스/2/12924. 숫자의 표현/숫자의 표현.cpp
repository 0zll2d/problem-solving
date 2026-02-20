#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    
    // digit = 연속된 자연수의 개수 (1개 ~ n개까지 시도)
    for(int digit = 1; digit <= n; digit++) {
        
        // 가운데 값 (대략적인 중심값)
        // n을 digit으로 나눈 몫을 중심값으로 설정
        int mid = n / digit;
        
        // 중심에서 좌우로 퍼질 거리
        // 예: digit=5 -> dist=2 (mid-2 ~ mid+2)
        int dist = (digit - 1) / 2;
        
        // 합 계산 시작값 (중심값)
        int sum = mid;
        
        // 시작값이 1 이상이어야 하므로
        // 가장 작은 값(mid - dist)이 0 이하라면 더 이상 진행 불가
        if(mid - dist <= 0) {
            break;
        }
        
        // digit이 짝수인 경우
        // 가운데가 두 개 존재 (mid, mid+1)
        if(digit % 2 == 0) {
            int mid2 = mid + 1;
            
            // 가운데 두 수 더하기
            sum += mid2;
            
            // 좌우 대칭으로 확장하면서 합 계산
            // 예: mid=5, mid2=6, digit=4
            // -> (4,5,6,7)
            for(int j = 1; j <= dist; j++) {
                sum += (mid - j) + (mid2 + j);
            }
            
        } 
        // digit이 홀수인 경우
        // 가운데가 하나 (mid)
        else {
            // 좌우 대칭 확장
            // 예: mid=7, digit=3
            // -> (6,7,8)
            for(int j = 0; j < dist; j++) {
                sum += (mid - j) + (mid + j);
            }
        }
        
        // 계산된 합이 n과 같으면
        if(sum == n) {
            answer++;
        }
    }
    
    return answer;
}