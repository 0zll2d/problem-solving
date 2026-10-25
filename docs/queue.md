# Queue API — Java vs C++

> Java 11 / C++11 기준  
> `E`: Java 원소 자료형 / `T`: C++ 원소 자료형

## 1. 자료구조 비교

| Java                       | C++        | 순서            | 삽입·삭제·조회 |
| -------------------------- | ---------- | --------------- | -------------- |
| `Deque<E>` (`ArrayDeque`)  | `queue<T>` | FIFO (선입선출) | O(1)           |

## 2. 기본 API

| 기능        | Java                         | C++                     | 시간복잡도 |
| ----------- | ---------------------------- | ----------------------- | ---------- |
| 뒤에 삽입   | `boolean offer(E e)` / `add` | `void push(const T& x)` | 평균 O(1)  |
| front 조회  | `E peek()`                   | `T& front()`            | O(1)       |
| back 조회   | `E peekLast()`               | `T& back()`             | O(1)       |
| front 제거  | `E poll()`                   | `void pop()`            | O(1)       |
| 비어 있는지 | `boolean isEmpty()`          | `bool empty()`          | O(1)       |
| 크기        | `int size()`                 | `size_t size()`         | O(1)       |
| 전체 삭제   | `void clear()`               | -                       | O(n)       |

> **주의**
>
> - Java `offer()`는 내부 배열이 꽉 차면 크기를 늘리며 복사하므로 평균 O(1)이다.
> - Java `poll()`은 제거한 값을 반환하지만, C++ `pop()`은 반환값이 없다.
> - Java `Deque`에서 `offer()`는 `offerLast()`, `poll()`은 `pollFirst()`, `peek()`은 `peekFirst()`와 같다.
> - Java `ArrayDeque`는 `null`을 저장할 수 없다.
> - C++ `queue`에는 `clear()`가 없으며, `q = queue<T>();`로 비운다.

## 3. Queue 순회

### Java

```java
for (int x : q) {
    // front → back 순서
}
```

### C++11

```cpp
while (!q.empty()) {
    int x = q.front(); // front → back 순서
    q.pop();
}
```

> C++ `queue`는 반복자(iterator)를 제공하지 않으므로 원소를 꺼내며 순회한다.

## 4. Queue 비교

| 기능           | Java | C++        |
| -------------- | ---- | ---------- |
| 전체 원소 비교 | -    | `q1 == q2` |

Java `ArrayDeque`는 `equals()`를 재정의하지 않아 객체 참조를 비교한다.

---

## 5. 주요 API 예제

### 5.1. Java poll() vs C++ pop()

| API           | 동작       | 반환값      |
| ------------- | ---------- | ----------- |
| Java `poll()` | front 제거 | 제거한 원소 |
| C++ `pop()`   | front 제거 | 없음        |

**Java**

```java
Deque<Integer> q = new ArrayDeque<>();

q.offer(1);
q.offer(2);

int x = q.poll(); // 1
```

**C++**

```cpp
queue<int> q;

q.push(1);
q.push(2);

int x = q.front(); // 1
q.pop();
```

### 5.2. 빈 큐 접근

| API              | 결과                          |
| ---------------- | ----------------------------- |
| Java `peek()`    | `null` 반환                   |
| Java `poll()`    | `null` 반환                   |
| Java `element()` | `NoSuchElementException` 발생 |
| Java `remove()`  | `NoSuchElementException` 발생 |
| C++ `front()`    | 정의되지 않은 동작 (UB)       |
| C++ `back()`     | 정의되지 않은 동작 (UB)       |
| C++ `pop()`      | 정의되지 않은 동작 (UB)       |

> Java에서 `int x = q.poll();`처럼 언박싱하면 빈 큐일 때 `NullPointerException`이 난다.  
> C++에서는 `front()`, `pop()` 호출 전 반드시 `empty()`로 확인한다.

### 5.3. Java 예외 API vs 반환값 API

| 기능       | 예외 발생     | 특수값 반환 |
| ---------- | ------------- | ----------- |
| 삽입       | `add(e)`      | `offer(e)`  |
| front 제거 | `remove()`    | `poll()`    |
| front 조회 | `element()`   | `peek()`    |

> `ArrayDeque`처럼 크기 제한이 없는 큐에서는 `add()`와 `offer()`가 같게 동작한다. 코딩테스트에서는 `offer()`, `poll()`, `peek()` 조합을 주로 쓴다.

### 5.4. ArrayDeque 큐 API vs Deque API

| 큐 API    | 대응 Deque API  | 위치 |
| --------- | --------------- | ---- |
| `offer()` | `offerLast()`   | 뒤쪽 |
| `poll()`  | `pollFirst()`   | 앞쪽 |
| `peek()`  | `peekFirst()`   | 앞쪽 |
| -         | `peekLast()`    | 뒤쪽 |

```java
Deque<Integer> q = new ArrayDeque<>();

q.offer(1);
q.offer(2);
q.offer(3);

System.out.println(q);            // [1, 2, 3] — 앞쪽이 front
System.out.println(q.peekLast()); // 3
```

> 큐로 사용할 때는 `offer()`, `poll()`, `peek()`만 사용한다. `push()`는 앞쪽에 넣는 스택 API라서 섞으면 순서가 꼬인다.

```java
Deque<Integer> q = new ArrayDeque<>();

q.offer(1);
q.push(2); // 앞쪽에 삽입

System.out.println(q.poll()); // 2 — 1이 아니다
```

### 5.5. Java ArrayDeque vs LinkedList

| 클래스       | 구조                | `null` 저장 | 권장 |
| ------------ | ------------------- | ----------- | ---- |
| `ArrayDeque` | 가변 크기 원형 배열 | X           | O    |
| `LinkedList` | 이중 연결 리스트    | O           | △    |

> `LinkedList`는 노드마다 객체를 만들어 메모리를 더 쓰고 캐시 효율이 떨어진다. 특별한 이유가 없으면 `ArrayDeque`를 쓴다.  
> `LinkedList`에 `null`을 넣으면 `poll()`의 `null`이 "빈 큐"인지 "`null` 원소"인지 구분할 수 없다.

### 5.6. BFS 기본 형태

**Java**

```java
Deque<int[]> q = new ArrayDeque<>();
boolean[][] visited = new boolean[n][m];
int[] dr = {-1, 1, 0, 0};
int[] dc = {0, 0, -1, 1};

q.offer(new int[]{0, 0});
visited[0][0] = true;

while (!q.isEmpty()) {
    int[] cur = q.poll();

    for (int d = 0; d < 4; d++) {
        int nr = cur[0] + dr[d];
        int nc = cur[1] + dc[d];

        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
        if (visited[nr][nc]) continue;

        visited[nr][nc] = true; // 넣을 때 방문 처리
        q.offer(new int[]{nr, nc});
    }
}
```

**C++**

```cpp
queue<pair<int, int>> q;
vector<vector<bool>> visited(n, vector<bool>(m, false));
int dr[] = {-1, 1, 0, 0};
int dc[] = {0, 0, -1, 1};

q.push({0, 0});
visited[0][0] = true;

while (!q.empty()) {
    pair<int, int> cur = q.front();
    q.pop();

    for (int d = 0; d < 4; d++) {
        int nr = cur.first + dr[d];
        int nc = cur.second + dc[d];

        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
        if (visited[nr][nc]) continue;

        visited[nr][nc] = true; // 넣을 때 방문 처리
        q.push({nr, nc});
    }
}
```

> 꺼낼 때 방문 처리하면 같은 칸이 큐에 여러 번 들어가 시간·메모리가 늘어난다. 큐에 **넣을 때** 방문 처리한다.

---

## Reference

- [Java 11 Deque API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html)
- [Java 11 ArrayDeque API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html)
- [C++ std::queue](https://en.cppreference.com/w/cpp/container/queue)
