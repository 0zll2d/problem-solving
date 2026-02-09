#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    vector<int> F(n + 1);
    F[0] = 0;
    F[1] = 1;
    
    for(int i = 2; i <= n; i++) {
        F[i] = (F[i - 2] + F[i - 1]) % 1234567;
    }

    return F[n];
}