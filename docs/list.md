# List API — Java vs C++

> Java 11 / C++11 기준  
> `E`: Java 원소 자료형 / `T`: C++ 원소 자료형

## 1. 자료구조 비교

| Java            | C++         | 구조             | 인덱스 접근 | 중간 삽입·삭제 |
| --------------- | ----------- | ---------------- | ----------- | -------------- |
| `ArrayList<E>`  | `vector<T>` | 동적 배열        | O(1)        | O(N)           |
| `LinkedList<E>` | `list<T>`   | 이중 연결 리스트 | O(N)        | O(1) — 위치를 알 때 |

> 코딩테스트에서는 대부분 `ArrayList` / `vector`를 쓴다.

## 2. 기본 API

`ArrayList` / `vector` 기준

| 기능           | Java                        | C++                                  | 시간복잡도 |
| -------------- | --------------------------- | ------------------------------------ | ---------- |
| 뒤에 삽입      | `boolean add(E e)`          | `void push_back(const T& x)`         | 평균 O(1)  |
| 중간 삽입      | `void add(int i, E e)`      | `iterator insert(iterator pos, const T& x)` | O(N) |
| 인덱스 조회    | `E get(int i)`              | `T& operator[](size_t i)` / `at(i)`  | O(1)       |
| 수정           | `E set(int i, E e)`         | `v[i] = x`                           | O(1)       |
| 맨 뒤 조회     | `E get(size() - 1)`         | `T& back()`                          | O(1)       |
| 맨 뒤 삭제     | `E remove(size() - 1)`      | `void pop_back()`                    | O(1)       |
| 인덱스로 삭제  | `E remove(int i)`           | `iterator erase(iterator pos)`       | O(N)       |
| 값으로 삭제    | `boolean remove(Object o)`  | `erase(remove(...), end())`          | O(N)       |
| 포함 여부      | `boolean contains(Object o)` | `find(v.begin(), v.end(), x)`       | O(N)       |
| 위치 찾기      | `int indexOf(Object o)`     | `find(...) - v.begin()`              | O(N)       |
| 크기           | `int size()`                | `size_t size()`                      | O(1)       |
| 비어 있는지    | `boolean isEmpty()`         | `bool empty()`                       | O(1)       |
| 전체 삭제      | `void clear()`              | `void clear()`                       | O(N)       |

> **주의**
>
> - Java `add()`, C++ `push_back()`은 내부 배열이 꽉 차면 크기를 늘리며 복사하므로 평균 O(1)이다.
> - Java `remove(int i)`는 제거한 원소를, `remove(Object o)`는 삭제 성공 여부를 반환한다.
> - Java `indexOf()`는 원소가 없으면 `-1`을 반환한다. C++ `find()`는 `end()`를 반환한다.
> - C++ `v[i]`는 범위를 검사하지 않는다(UB). `at(i)`는 범위를 벗어나면 `std::out_of_range` 예외가 발생한다.
> - Java `get(i)`는 범위를 벗어나면 `IndexOutOfBoundsException`이 발생한다.
> - C++ `find()`, `remove()`는 `<algorithm>`에 있다.

## 3. List 순회

### Java

```java
for (int x : list) {
    // 앞 → 뒤 순서
}

for (int i = 0; i < list.size(); i++) {
    int x = list.get(i);
}
```

### C++11

```cpp
for (int x : v) {
    // 앞 → 뒤 순서
}

for (size_t i = 0; i < v.size(); i++) {
    int x = v[i];
}
```

> `LinkedList`에서 `get(i)`로 순회하면 매번 처음부터 찾아가서 O(N²)이 된다. 연결 리스트는 for-each로 순회한다.

## 4. List 비교

| 기능           | Java                         | C++        |
| -------------- | ---------------------------- | ---------- |
| 전체 원소 비교 | `boolean equals(Object obj)` | `v1 == v2` |

서로 다른 List 객체라도 같은 순서로 같은 원소를 가지면 동일한 List로 판단한다.

---

## 5. 주요 API 예제

### 5.1. 생성·초기화

**Java**

```java
List<Integer> list = new ArrayList<>();
List<Integer> list2 = new ArrayList<>(List.of(1, 2, 3));     // 값으로 초기화
List<Integer> list3 = new ArrayList<>(Collections.nCopies(5, 0)); // [0, 0, 0, 0, 0]
```

**C++**

```cpp
vector<int> v;
vector<int> v2 = {1, 2, 3};     // 값으로 초기화
vector<int> v3(5, 0);           // {0, 0, 0, 0, 0}
vector<vector<int>> grid(n, vector<int>(m, 0)); // n × m 2차원
```

### 5.2. Java List.of() vs Arrays.asList() vs new ArrayList<>()

| 생성 방법                | `add()` / `remove()` | `set()` | `null` 저장 |
| ------------------------ | -------------------- | ------- | ----------- |
| `List.of(...)`           | X (예외)             | X (예외) | X          |
| `Arrays.asList(...)`     | X (예외)             | O       | O           |
| `new ArrayList<>(...)`   | O                    | O       | O           |

> `List.of()`, `Arrays.asList()`에 원소를 추가하거나 삭제하면 `UnsupportedOperationException`이 발생한다. 수정할 List가 필요하면 `new ArrayList<>(...)`로 감싼다.

### 5.3. Java remove(int) vs remove(Object)

`List<Integer>`에서는 `remove(1)`이 **인덱스 1**을 지운다.

```java
List<Integer> list = new ArrayList<>(List.of(10, 1, 20));

list.remove(1);                  // 인덱스 1 삭제 → [10, 20]
list.remove(Integer.valueOf(10)); // 값 10 삭제   → [20]
```

> 값으로 지우려면 `Integer.valueOf(x)`나 `(Integer) x`로 넘긴다.

### 5.4. C++ 값으로 삭제 — erase-remove

```cpp
vector<int> v = {1, 2, 1, 3};

v.erase(remove(v.begin(), v.end(), 1), v.end()); // {2, 3}
```

> `remove()`는 지울 원소를 뒤로 밀고 새 끝 위치를 반환할 뿐, 크기를 줄이지 않는다. `erase()`까지 해야 실제로 지워진다.  
> Java `remove(Object)`는 **첫 번째 하나만** 지우고, C++ erase-remove는 **모두** 지운다. Java에서 모두 지우려면 `list.removeIf(x -> x == 1)`을 쓴다.

### 5.5. 순회 중 삭제

**Java**

```java
// for-each 안에서 list.remove() → ConcurrentModificationException
list.removeIf(x -> x % 2 == 0);

// 또는 Iterator 사용
Iterator<Integer> it = list.iterator();
while (it.hasNext()) {
    if (it.next() % 2 == 0) it.remove();
}
```

**C++**

```cpp
for (auto it = v.begin(); it != v.end(); ) {
    if (*it % 2 == 0) it = v.erase(it); // erase가 다음 위치를 반환
    else ++it;
}
```

### 5.6. 정렬

| 기능      | Java                                             | C++                                      | 시간복잡도 |
| --------- | ------------------------------------------------ | ---------------------------------------- | ---------- |
| 오름차순  | `Collections.sort(list)`                         | `sort(v.begin(), v.end())`               | O(N log N) |
| 내림차순  | `list.sort(Collections.reverseOrder())`          | `sort(v.begin(), v.end(), greater<int>())` | O(N log N) |
| 뒤집기    | `Collections.reverse(list)`                      | `reverse(v.begin(), v.end())`            | O(N)       |

```java
List<int[]> list = new ArrayList<>();
list.sort((a, b) -> Integer.compare(a[0], b[0])); // 첫 번째 값 오름차순
```

```cpp
vector<pair<int, int>> v;
sort(v.begin(), v.end()); // first → second 순서로 오름차순
```

### 5.7. Java Integer 비교 주의

```java
List<Integer> list = new ArrayList<>(List.of(1000, 1000));

list.get(0) == list.get(1);       // false — 객체 참조 비교
list.get(0).equals(list.get(1));  // true
```

> `Integer`는 `-128 ~ 127`만 캐싱하므로 그 범위를 벗어나면 `==`가 `false`가 될 수 있다. `equals()`를 쓰거나 `int`로 꺼내서 비교한다.

### 5.8. Java List ↔ 배열 변환

```java
// List<Integer> → int[]
int[] arr = list.stream().mapToInt(Integer::intValue).toArray();

// int[] → List<Integer>
List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
```

### 5.9. 인접 리스트 (그래프)

**Java**

```java
List<List<Integer>> graph = new ArrayList<>();
for (int i = 0; i < n; i++) graph.add(new ArrayList<>());

graph.get(u).add(v);
graph.get(v).add(u);
```

**C++**

```cpp
vector<vector<int>> graph(n);

graph[u].push_back(v);
graph[v].push_back(u);
```

---

## Reference

- [Java 11 List API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/List.html)
- [Java 11 ArrayList API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayList.html)
- [C++ std::vector](https://en.cppreference.com/w/cpp/container/vector)
- [C++ std::list](https://en.cppreference.com/w/cpp/container/list)
