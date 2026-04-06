#include <iostream>
#include <vector>
#include <deque>
#include <cstring>

using namespace std;

int N, K, L, X, x, y;
char C;
int grid[101][101];
vector<pair<int, int>> changes;
int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { 1, 0, -1, 0 };

bool isOutOfBounds(int x, int y) {
	return (x < 1 || x > N || y < 1 || y > N);
}

int main() {
	int answer = 0;

	cin >> N >> K;

	for (int i = 0; i < K; i++) {
		cin >> x >> y;

		grid[x][y] = 1;
	}

	cin >> L;

	for (int i = 0; i < L; i++) {
		cin >> X >> C;

		if (C == 'L') {
			changes.push_back({ X, 3 });
		}
		else if (C == 'D') {
			changes.push_back({ X, 1 });
		}
	}
	
	deque<pair<int, int>> snake;
	bool visited[101][101];
	int dir = 0;
	int idx = 0;

	memset(visited, false, sizeof(visited));

	snake.push_back({ 1, 1 });
	visited[1][1] = true;

	while (!snake.empty()) {
		answer++;	// 1초 시작

		// 이동한 뱀의 머리
		int nx = snake.back().first + dx[dir];
		int ny = snake.back().second + dy[dir];

		// 벽 또는 자기 자신에 부딪히는 경우 게임 종료
		if (isOutOfBounds(nx, ny) || visited[nx][ny]) {
			break;
		}

		snake.push_back({ nx, ny });
		visited[nx][ny] = true;

		// 이동한 칸에 사과가 없으면
		if (grid[nx][ny] == 0) {
			// 꼬리 제거
			int tx = snake.front().first;
			int ty = snake.front().second;

			snake.pop_front();
			visited[tx][ty] = false;
		}
		else {
			grid[nx][ny] = 0;
		}

		// X초 후 방향 전환
		if (idx < changes.size() && answer == changes[idx].first) {
			dir = (dir + changes[idx].second) % 4;
			idx++;
		}
	}

	cout << answer << endl;

	return 0;
}