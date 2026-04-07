#include <iostream>
#include <vector>
#include <queue>
#include <tuple>
#include <cstring>
#include <climits>
#include <algorithm>

using namespace std;

int N, M;
int grid[50][50];
vector<pair<int, int>> virus;	// 2 바이러스
vector<pair<int, int>> active;	// 활성 바이러스
int dx[4] = { -1, 1, 0, 0 };
int dy[4] = { 0, 0, -1, 1 };
int answer = INT_MAX;

bool isOutOfBounds(int x, int y) {
	return (x < 0 || x >= N || y < 0 || y >= N);
}

int bfs() {
	queue<tuple<int, int, int>> q;
	bool visited[50][50];
	int result = -1;

	memset(visited, false, sizeof(visited));

	for (int i = 0; i < active.size(); i++) {
		int x = active[i].first;
		int y = active[i].second;

		q.emplace(x, y, 0);	// q.push(make_tuple(x, y, 0)); 와 동일
		visited[x][y] = true;
	}

	while (!q.empty()) {
		int x = get<0>(q.front());
		int y = get<1>(q.front());
		int sec = get<2>(q.front());
		q.pop();

		if (grid[x][y] == 0) {
			result = max(result, sec);	// 비활성 바이러스는 지나갈 수 있지만 시간 계산에 포함하지 않음
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isOutOfBounds(nx, ny) && !visited[nx][ny] && grid[nx][ny] != 1) {
				q.emplace(nx, ny, sec + 1);
				visited[nx][ny] = true;
			}
		}

	}

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (grid[i][j] == 0 && !visited[i][j]) {
				return INT_MAX;
			}
		}
	}

	return result;
}

// 바이러스 중 M개 바이러스 선택
void dfs(int idx) {
	if (active.size() == M) {
		answer = min(answer, bfs());
		return;
	}

	for (int i = idx; i < virus.size(); i++) {
		int x = virus[i].first;
		int y = virus[i].second;

		active.push_back({ x, y });

		dfs(i + 1);

		active.pop_back();
	}
}

int main() {
	cin >> N >> M;

	int zero = 0;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> grid[i][j];

			if (grid[i][j] == 0) {
				zero++;
			}
			if (grid[i][j] == 2) {
				virus.push_back({ i, j });
			}
		}
	}

	if (zero == 0) {	// 빈 칸의 개수가 아무것도 없는 경우(TC7)
		cout << 0 << endl;
		return 0;
	}

	dfs(0);

	cout << (answer == INT_MAX ? -1 : answer) << endl;
	
	return 0;
}