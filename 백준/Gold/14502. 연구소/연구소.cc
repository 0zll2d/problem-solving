#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
#include <algorithm>

using namespace std;

int N, M;
int grid[8][8];
vector<pair<int, int>> zero;	// 0
vector<pair<int, int>> virus;	// 2
vector<pair<int, int>> wall;	// 1
int dx[4] = { -1, 1, 0 ,0 };
int dy[4] = { 0, 0, -1, 1 };
int answer;

bool isOutOfBounds(int x, int y) {
	return (x < 0 || x >= N || y < 0 || y >= M);
}

// 2. 바이러스 확산 및 안전 영역 확인
int bfs() {
	queue<pair<int, int>> q;
	bool visited[8][8];
	int result = 0;

	memset(visited, false, sizeof(visited));

	for (int i = 0; i < virus.size(); i++) {
		q.push({ virus[i].first, virus[i].second });
	}

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isOutOfBounds(nx, ny) && !visited[nx][ny] && grid[nx][ny] == 0) {
				q.push({ nx, ny });
				visited[nx][ny] = true;
				result++;
			}
		}
	}

	return result;
}

// 1. 0인 칸 중에서 3개 벽 세우기(조합)
void dfs(int idx) {
	if (wall.size() == 3) {
		answer = max(answer, (int)(zero.size() - bfs() - 3));
		return;
	}

	for (int i = idx; i < zero.size(); i++) {
		int x = zero[i].first;
		int y = zero[i].second;

		// 벽으로 선택
		grid[x][y] = 1;
		wall.push_back({ x, y });

		dfs(i + 1);

		// 백트래킹
		grid[x][y] = 0;
		wall.pop_back();
	}
}

int main() {
	
	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> grid[i][j];

			if (grid[i][j] == 0) {
				zero.push_back({ i, j });
			}else if(grid[i][j] == 2) {
				virus.push_back({ i, j });
			}
		}
	}

	answer = 0;

	dfs(0);

	cout << answer << endl;

	return 0;
}