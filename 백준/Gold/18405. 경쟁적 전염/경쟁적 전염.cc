#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N, K;
int S, X, Y;
int grid[200][200];
vector<pair<int, int>> virus;
int dx[4] = { -1, 1, 0 ,0 };
int dy[4] = { 0, 0, -1, 1 };

bool isOutOfBounds(int x, int y) {
	return (x < 0 || x >= N || y < 0 || y >= N);
}

void bfs() {
	queue<vector<int>> q;

	for (int i = 0; i < virus.size(); i++) {
		int x = virus[i].first;
		int y = virus[i].second;
		int num = grid[x][y];

		q.push({ x, y, num, 0 });
	}

	while (!q.empty()) {
		int x = q.front()[0];
		int y = q.front()[1];
		int num = q.front()[2];
		int sec = q.front()[3];
		q.pop();

		if (sec >= S) {
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isOutOfBounds(nx, ny) && grid[nx][ny] == 0) {
				q.push({ nx, ny, num, sec + 1 });
				grid[nx][ny] = num;
			}
		}
	}

	return;
}

int main() {
	int answer = 0;

	cin >> N >> K;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> grid[i][j];

			if (grid[i][j] != 0) {
				virus.push_back({ i, j });
			}
		}
	}

	cin >> S >> X >> Y;

	sort(virus.begin(), virus.end(), [](const pair<int, int>& a, const pair<int, int>& b) {
		return grid[a.first][a.second] < grid[b.first][b.second];
	});

	bfs();

	cout << grid[X - 1][Y - 1] << endl;

	return 0;
}