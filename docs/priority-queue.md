# PriorityQueue API — Java vs C++

> Java 11 / C++11 기준  
> `E`: Java 원소 자료형 / `T`: C++ 원소 자료형

## 1. 자료구조 비교

| Java               | C++                 | 내부 구조 | 기본 정렬            |
| ------------------ | ------------------- | --------- | -------------------- |
| `PriorityQueue<E>` | `priority_queue<T>` | 이진 힙   | Java 최소힙 / C++ 최대힙 |

> **기본값이 서로 반대다.** Java는 가장 작은 값이, C++은 가장 큰 값이 top에 온다.

## 2. 기본 API

| 기능        | Java                         | C++                     | 시간복잡도 |
| ----------- | ---------------------------- | ----------------------- | ---------- |
| 삽입        | `boolean offer(E e)` / `add` | `void push(const T& x)` | O(log n)   |
| top 조회    | `E peek()`                   | `const T& top()`        | O(1)       |
| top 제거    | `E poll()`                   | `void pop()`            | O(log n)   |
| 비어 있는지 | `boolean isEmpty()`          | `bool empty()`          | O(1)       |
| 크기        | `int size()`                 | `size_t size()`         | O(1)       |
| 포함 여부   | `boolean contains(Object o)` | -                       | O(n)       |
| 특정 원소 제거 | `boolean remove(Object o)` | -                       | O(n)       |
| 전체 삭제   | `void clear()`               | -                       | O(n)       |

> **주의**
>
> - Java `poll()`은 제거한 값을 반환하지만, C++ `pop()`은 반환값이 없다.
> - Java `PriorityQueue`는 `null`을 저장할 수 없다.
> - C++ `priority_queue`에는 `clear()`가 없으며, `pq = priority_queue<T>();`로 비운다.

## 3. 최소힙 / 최대힙 선언

### 3.1. Java

```java
// 최소힙 (기본)
PriorityQueue<Integer> minHeap = new PriorityQueue<>();

// 최대힙
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
PriorityQueue<Integer> maxHeap2 = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
```

### 3.2. C++11

```cpp
#include <queue>
#include <vector>
#include <functional> // greater

// 최대힙 (기본)
priority_queue<int> maxHeap;

// 최소힙
priority_queue<int, vector<int>, greater<int>> minHeap;
```

> C++11에서는 `greater<int>`처럼 타입을 명시해야 한다. `greater<>`는 C++14부터 사용할 수 있다.

### 3.3. 동작 비교

입력 순서: `3, 1, 4, 1, 5`

| 힙     | 꺼내는 순서     |
| ------ | --------------- |
| 최소힙 | `1, 1, 3, 4, 5` |
| 최대힙 | `5, 4, 3, 1, 1` |

**Java**

```java
PriorityQueue<Integer> minHeap = new PriorityQueue<>();
for (int x : new int[]{3, 1, 4, 1, 5}) minHeap.offer(x);

while (!minHeap.isEmpty()) {
    System.out.print(minHeap.poll() + " "); // 1 1 3 4 5
}
```

**C++**

```cpp
priority_queue<int, vector<int>, greater<int>> minHeap;
for (int x : {3, 1, 4, 1, 5}) minHeap.push(x);

while (!minHeap.empty()) {
    cout << minHeap.top() << " "; // 1 1 3 4 5
    minHeap.pop();
}
```

## 4. 비교 기준 정하기

### 4.1. 비교자 의미

| 언어 | 비교자가 표현하는 것                         | `a < b` 비교자의 결과 |
| ---- | -------------------------------------------- | --------------------- |
| Java | 음수를 반환하면 `a`가 **먼저** 나온다        | 최소힙                |
| C++  | `true`를 반환하면 `a`가 **나중에** 나온다    | 최대힙                |

> C++ 비교자는 "`a`의 우선순위가 `b`보다 낮은가"로 읽는다. 그래서 `less`(기본값)가 최대힙, `greater`가 최소힙이 된다.

### 4.2. 배열 / pair — 첫 번째 값 오름차순, 같으면 두 번째 값 오름차순

**Java**

```java
PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
    if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
    return Integer.compare(a[1], b[1]);
});

pq.offer(new int[]{2, 1});
pq.offer(new int[]{1, 5});
pq.offer(new int[]{1, 3});

int[] top = pq.poll(); // [1, 3]
```

**C++**

```cpp
// pair는 first → second 순서로 비교하므로 greater만 지정하면 된다
priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

pq.push({2, 1});
pq.push({1, 5});
pq.push({1, 3});

pair<int, int> top = pq.top(); // {1, 3}
pq.pop();
```

### 4.3. 사용자 정의 비교자

**Java — 객체 필드 기준 최대힙**

```java
class Node {
    int id, cost;
    Node(int id, int cost) { this.id = id; this.cost = cost; }
}

PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.cost, a.cost));
// 또는
PriorityQueue<Node> pq2 = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.cost).reversed());
```

**C++11 — 구조체 필드 기준 최소힙**

```cpp
struct Node {
    int id, cost;
};

struct Cmp {
    bool operator()(const Node& a, const Node& b) const {
        return a.cost > b.cost; // cost가 큰 쪽이 나중에 → 최소힙
    }
};

priority_queue<Node, vector<Node>, Cmp> pq;
```

**C++11 — 람다 비교자**

```cpp
auto cmp = [](const Node& a, const Node& b) { return a.cost > b.cost; };
priority_queue<Node, vector<Node>, decltype(cmp)> pq(cmp);
```

> 람다를 쓸 때는 생성자에 `cmp`를 반드시 넘겨야 한다. C++11 람다는 기본 생성할 수 없다.

---

## 5. 주의사항

### 5.1. Java `a - b` 비교자 오버플로

```java
// 위험: a = Integer.MIN_VALUE, b = 1 이면 오버플로로 부호가 뒤집힌다
PriorityQueue<Integer> bad = new PriorityQueue<>((a, b) -> a - b);

// 안전
PriorityQueue<Integer> good = new PriorityQueue<>((a, b) -> Integer.compare(a, b));
```

> 값 범위가 작다고 확신할 때만 `a - b`를 쓴다. 기본은 `Integer.compare()`다.

### 5.2. C++ 음수 트릭

최대힙에 `-x`를 넣어 최소힙처럼 쓰는 방법도 있다.

```cpp
priority_queue<int> pq;
pq.push(-3);
pq.push(-1);

int x = -pq.top(); // 1
```

> `INT_MIN`은 부호를 바꾸면 오버플로가 난다. 값 범위를 확인하고 쓴다.

### 5.3. 빈 힙 접근

| API              | 결과                          |
| ---------------- | ----------------------------- |
| Java `peek()`    | `null` 반환                   |
| Java `poll()`    | `null` 반환                   |
| Java `element()` | `NoSuchElementException` 발생 |
| Java `remove()`  | `NoSuchElementException` 발생 |
| C++ `top()`      | 정의되지 않은 동작 (UB)       |
| C++ `pop()`      | 정의되지 않은 동작 (UB)       |

> Java에서 `int x = pq.poll();`처럼 언박싱하면 빈 힙일 때 `NullPointerException`이 난다.

### 5.4. 순회 순서

```java
PriorityQueue<Integer> pq = new PriorityQueue<>(List.of(3, 1, 4, 1, 5));

System.out.println(pq); // [1, 1, 4, 3, 5] — 정렬 순서가 아니다
```

> Java 반복자와 `toString()`은 내부 배열 순서를 따른다. 정렬된 순서가 필요하면 `poll()`로 꺼낸다.  
> C++ `priority_queue`는 반복자를 제공하지 않는다.

### 5.5. 한 번에 생성 (heapify)

| 언어 | 코드                                          | 시간복잡도 |
| ---- | --------------------------------------------- | ---------- |
| Java | `new PriorityQueue<>(list)`                   | O(n)       |
| C++  | `priority_queue<int> pq(v.begin(), v.end());` | O(n)       |

> 원소를 하나씩 넣으면 O(n log n)이다.

> Java에서 `new PriorityQueue<>(list)`는 자연 순서(최소힙)만 쓴다. 최대힙이면 비교자로 생성한 뒤 `addAll(list)`를 호출한다(O(n log n)).

---

## Reference

- [Java 11 PriorityQueue API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/PriorityQueue.html)
- [Java 11 Comparator API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Comparator.html)
- [C++ std::priority_queue](https://en.cppreference.com/w/cpp/container/priority_queue)
