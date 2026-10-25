# Set API — Java vs C++

> Java 11 / C++11 기준  
> `E`: Java 원소 자료형 / `T`: C++ 원소 자료형

## 1. 자료구조 비교

| Java         | C++                | 원소 정렬 | 중복 | 조회      |
| ------------ | ------------------ | --------- | ---- | --------- |
| `HashSet<E>` | `unordered_set<T>` | X         | X    | 평균 O(1) |
| `TreeSet<E>` | `set<T>`           | 오름차순  | X    | O(log N)  |

## 2. 기본 API

| 기능        | Java                        | C++                                   | Hash      | Tree     |
| ----------- | --------------------------- | ------------------------------------- | --------- | -------- |
| 삽입        | `boolean add(E e)`          | `pair<iterator, bool> insert(const T& x)` | 평균 O(1) | O(log N) |
| 존재 확인   | `boolean contains(Object o)` | `size_t count(const T& x)`           | 평균 O(1) | O(log N) |
| 원소 탐색   | -                           | `iterator find(const T& x)`           | 평균 O(1) | O(log N) |
| 삭제        | `boolean remove(Object o)`  | `size_t erase(const T& x)`            | 평균 O(1) | O(log N) |
| 크기        | `int size()`                | `size_t size()`                       | O(1)      | O(1)     |
| 비어 있는지 | `boolean isEmpty()`         | `bool empty()`                        | O(1)      | O(1)     |
| 전체 삭제   | `void clear()`              | `void clear()`                        | O(N)      | O(N)     |

> Hash: `HashSet` / `unordered_set`, Tree: `TreeSet` / `set`

> **주의**
>
> - Java `add()`는 이미 있는 원소면 `false`를 반환하고 아무것도 바꾸지 않는다.
> - C++ `insert()`의 `.second`가 삽입 성공 여부(`bool`)다.
> - C++ `find()`는 원소가 없으면 `end()`를 반환한다.
> - Hash 계열은 해시 충돌이 많으면 최악 O(N)까지 느려진다.
> - Java `TreeSet`은 `null`을 저장할 수 없다.

## 3. Set 순회

### Java

```java
for (int x : set) {
    // HashSet: 순서 보장 X / TreeSet: 오름차순
}
```

### C++11

```cpp
for (int x : s) {
    // unordered_set: 순서 보장 X / set: 오름차순
}
```

> 삽입 순서를 유지해야 하면 Java는 `LinkedHashSet`을 쓴다. C++에는 대응하는 컨테이너가 없다.

## 4. Set 비교

| 기능           | Java                         | C++        |
| -------------- | ---------------------------- | ---------- |
| 전체 원소 비교 | `boolean equals(Object obj)` | `s1 == s2` |

서로 다른 Set 객체라도 원소가 모두 같으면 동일한 Set으로 판단한다. Java는 `HashSet`과 `TreeSet`끼리도 비교할 수 있다.

---

## 5. 주요 API 예제

### 5.1. 중복 제거

**Java**

```java
int[] arr = {3, 1, 3, 2, 1};

Set<Integer> set = new HashSet<>();
for (int x : arr) set.add(x);

System.out.println(set.size()); // 3
```

**C++**

```cpp
vector<int> v = {3, 1, 3, 2, 1};

set<int> s(v.begin(), v.end());

cout << s.size(); // 3
```

### 5.2. add() / insert() 반환값 활용

이미 방문했는지 확인과 삽입을 한 번에 처리한다.

**Java**

```java
Set<String> visited = new HashSet<>();

if (!visited.add(key)) {
    // 이미 있던 원소
}
```

**C++**

```cpp
unordered_set<string> visited;

if (!visited.insert(key).second) {
    // 이미 있던 원소
}
```

### 5.3. 집합 연산

| 연산   | Java                | C++ (`<algorithm>`, 정렬된 범위) | 결과        |
| ------ | ------------------- | -------------------------------- | ----------- |
| 합집합 | `a.addAll(b)`       | `set_union`                      | A ∪ B       |
| 교집합 | `a.retainAll(b)`    | `set_intersection`               | A ∩ B       |
| 차집합 | `a.removeAll(b)`    | `set_difference`                 | A − B       |
| 부분집합 | `a.containsAll(b)` | `includes`                      | B ⊆ A 여부  |

**Java**

```java
Set<Integer> a = new HashSet<>(List.of(1, 2, 3));
Set<Integer> b = new HashSet<>(List.of(2, 3, 4));

Set<Integer> inter = new HashSet<>(a); // a를 보존하려면 복사 후 연산
inter.retainAll(b);                    // [2, 3]
```

**C++**

```cpp
set<int> a = {1, 2, 3};
set<int> b = {2, 3, 4};
set<int> inter;

set_intersection(a.begin(), a.end(), b.begin(), b.end(),
                 inserter(inter, inter.begin())); // {2, 3}
```

> Java `addAll()`, `retainAll()`, `removeAll()`은 **호출한 Set 자체를 바꾼다.**  
> C++ 집합 알고리즘은 정렬된 범위가 필요하므로 `set`에만 쓴다. `unordered_set`은 직접 순회하며 `count()`로 처리한다.

### 5.4. TreeSet / set 탐색 API

| 기능        | Java `TreeSet`   | C++ `set`                | Java     | C++        |
| --------- | ---------------- | ------------------------ | -------- | ---------- |
| 최솟값       | `E first()`      | `*s.begin()`             | O(log N) | O(1)       |
| 최댓값       | `E last()`       | `*s.rbegin()`            | O(log N) | O(1)       |
| `x` 이상 최소 | `E ceiling(E x)` | `s.lower_bound(x)`       | O(log N) | O(log N)   |
| `x` 초과 최소 | `E higher(E x)`  | `s.upper_bound(x)`       | O(log N) | O(log N)   |
| `x` 이하 최대 | `E floor(E x)`   | `prev(s.upper_bound(x))` | O(log N) | O(log N)   |
| `x` 미만 최대 | `E lower(E x)`   | `prev(s.lower_bound(x))` | O(log N) | O(log N)   |
| 최솟값 제거    | `E pollFirst()`  | `s.erase(s.begin())`     | O(log N) | 분할 상환 O(1) |
| 최댓값 제거    | `E pollLast()`   | `s.erase(prev(s.end()))` | O(log N) | 분할 상환 O(1) |

> C++ `begin()`, `rbegin()`은 가장 왼쪽·오른쪽 노드를 따로 가리키고 있어 O(1)이다. 반복자로 지우는 `erase(it)`는 다시 탐색하지 않으므로 분할 상환 O(1)이다.

**Java**

```java
TreeSet<Integer> set = new TreeSet<>(List.of(10, 20, 30));

set.ceiling(15); // 20
set.higher(20);  // 30
set.floor(25);   // 20
set.lower(10);   // null — 없으면 null
```

**C++**

```cpp
set<int> s = {10, 20, 30};

auto it = s.lower_bound(15);       // *it == 20
it = s.upper_bound(20);            // *it == 30

it = s.upper_bound(25);            // floor(25)
if (it != s.begin()) {
    int x = *prev(it);             // 20
}

it = s.lower_bound(10);            // lower(10)
if (it == s.begin()) {
    // 없음
}
```

> **주의**
>
> - Java `ceiling()`, `higher()`, `floor()`, `lower()`는 없으면 `null`을 반환한다. `int`로 언박싱하면 `NullPointerException`이 난다.
> - Java `first()`, `last()`는 빈 Set이면 `NoSuchElementException`이 발생한다. `pollFirst()`, `pollLast()`는 `null`을 반환한다.
> - C++ `lower_bound()`, `upper_bound()`는 없으면 `end()`를 반환한다. `prev()` 전에는 `begin()`인지 확인한다.
> - C++ 빈 `set`에서 `*s.begin()`, `*s.rbegin()`은 정의되지 않은 동작 (UB)이다.

### 5.5. C++ s.lower_bound() vs std::lower_bound()

| 호출                                    | 시간복잡도 |
| --------------------------------------- | ---------- |
| `s.lower_bound(x)` (멤버 함수)          | O(log N)   |
| `lower_bound(s.begin(), s.end(), x)`    | O(N)       |

> `set` 반복자는 임의 접근이 안 돼서 `std::lower_bound()`가 한 칸씩 이동한다. `set`에서는 반드시 **멤버 함수**를 쓴다.

---

## Reference

- [Java 11 Set API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Set.html)
- [Java 11 TreeSet API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/TreeSet.html)
- [C++ std::set](https://en.cppreference.com/w/cpp/container/set)
- [C++ std::unordered_set](https://en.cppreference.com/w/cpp/container/unordered_set)
