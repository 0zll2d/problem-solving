#include <iostream>
#include <cstdlib>
#include <cstring>

using namespace std;

int N, L, R;
int A[100][100];
int shared[100][100];	// 공유 기준 넘버링 확인
int unions[2500][2];		// info[i] { 연합 인구의 수, 연합을 이루고 있는 칸의 개수 }
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };

bool isOutOfBounds(int x, int y) {
	return (x < 0 || x >= N || y < 0 || y >= N);
}

void dfs(int x, int y, int num) {
	shared[x][y] = num;
	unions[num][0] += A[x][y];
	unions[num][1]++;

	for (int i = 0; i < 4; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];

		if (isOutOfBounds(nx, ny) || shared[nx][ny] != 0) {
			continue;
		}

		int diff = abs(A[nx][ny] - A[x][y]);

		if (diff >= L && diff <= R) {
			dfs(nx, ny, num);
		}
	}
}

bool check() {	// 인구 이동 일어나는지 확인
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			for (int d = 0; d < 4; d++) {
				int ni = i + dx[d];
				int nj = j + dy[d];

				if (isOutOfBounds(ni, nj)) {
					continue;
				}

				int diff = abs(A[ni][nj] - A[i][j]);

				if (diff >= L && diff <= R) {
					return true;
				}

			}
		}
	}

	return false;
}


int main() {
	int answer = 0;

	cin >> N >> L >> R;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> A[i][j];
		}
	}

	while (check()) {
		int num = 1;

		memset(shared, 0, sizeof(shared));
		memset(unions, 0, sizeof(unions));


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (shared[i][j] == 0) {
					dfs(i, j, num++);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				num = shared[i][j];
				A[i][j] = unions[num][0] / unions[num][1];
			}
		}

		answer++;
	}
	
	cout << answer << endl;

	return 0;
}