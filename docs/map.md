# Map API — Java vs C++

> Java 11 / C++11 기준  
> `K`: Key 자료형 / `V`: Value 자료형

## 1. 자료구조 비교

| Java            | C++                   | Key 정렬 | 조회      |
| --------------- | --------------------- | -------- | --------- |
| `HashMap<K, V>` | `unordered_map<K, V>` | X        | 평균 O(1) |
| `TreeMap<K, V>` | `map<K, V>`           | 오름차순 | O(log N)  |

## 2. 기본 API

| 기능                | Java                                         | C++                                         |
| ------------------- | -------------------------------------------- | ------------------------------------------- |
| 삽입·수정           | `V put(K key, V value)`                      | `m[key] = value`                            |
| Key 없을 때 삽입    | `V putIfAbsent(K key, V value)`              | `pair<iterator, bool> insert({key, value})` |
| Key 없을 때 값 생성 | `V computeIfAbsent(K key, Function f)`       | -                                           |
| 값 조회             | `V get(Object key)`                          | `V& at(const K& key)`                       |
| 기본값 조회         | `V getOrDefault(Object key, V defaultValue)` | -                                           |
| Key 존재 확인       | `boolean containsKey(Object key)`            | `size_t count(const K& key)`                |
| Key 탐색            | -                                            | `iterator find(const K& key)`               |
| Key 목록            | `Set<K> keySet()`                            | -                                           |
| Value 목록          | `Collection<V> values()`                     | -                                           |
| Key-Value 목록      | `Set<Map.Entry<K,V>> entrySet()`             | -                                           |
| Key 조회 (Entry)    | `K getKey()`                                 | `entry.first`                               |
| Value 조회 (Entry)  | `V getValue()`                               | `entry.second`                              |
| 삭제                | `V remove(Object key)`                       | `size_t erase(const K& key)`                |
| 크기                | `int size()`                                 | `size_t size()`                             |
| 비어 있는지         | `boolean isEmpty()`                          | `bool empty()`                              |
| 전체 삭제           | `void clear()`                               | `void clear()`                              |

> **주의**
>
> - Java `put()`은 이전 Value를 반환하며, 기존 Key가 없다면 `null`을 반환한다.
> - C++ `m[key]`는 Key가 없으면 기본값을 생성해 삽입한다.
> - C++ `at()`은 Key가 없으면 `std::out_of_range` 예외가 발생한다.
> - C++ `find()`는 Key가 없으면 `end()`를 반환한다.

## 3. Map 순회

### Java

```java
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    String key = entry.getKey();
    int value = entry.getValue();
}
```

### C++11

```cpp
for (const auto& entry : m) {
    string key = entry.first;
    int value = entry.second;
}
```

> C++ `map`의 원소는 `pair<const K, V>` 형태이며, `first`는 Key, `second`는 Value다.

## 4. Map 비교

| 기능                | Java                         | C++        |
| ------------------- | ---------------------------- | ---------- |
| 전체 Key-Value 비교 | `boolean equals(Object obj)` | `m1 == m2` |

서로 다른 Map 객체라도 Key-Value 쌍이 모두 같으면 동일한 Map으로 판단한다.

---

## 5. 주요 API 예제

### 5.1. put() vs putIfAbsent() vs computeIfAbsent()

| API                 | Key 없음               | Key 있음 (Value가 null이 아님) | 반환값                 |
| ------------------- | ---------------------- | ------------------------------ | ---------------------- |
| `put()`             | 값 저장                | 값 덮어쓰기                    | 이전 Value             |
| `putIfAbsent()`     | 전달한 값 저장         | 기존 값 유지                   | 이전 Value             |
| `computeIfAbsent()` | 함수로 값 생성 후 저장 | 기존 값 유지                   | 기존 또는 생성된 Value |

> `putIfAbsent()`와 `computeIfAbsent()`는 기존 Value가 `null`인 경우에도 새 값을 저장할 수 있다. 단, `computeIfAbsent()`의 함수가 `null`을 반환하면 저장하지 않는다.

**put() — 삽입·수정**

```java
Map<String, Integer> map = new HashMap<>();

map.put("apple", 10);
map.put("apple", 20); // 기존 값 덮어쓰기

System.out.println(map.get("apple")); // 20
```

**putIfAbsent() — Key 없을 때만 삽입**

```java
Map<String, Integer> map = new HashMap<>();

map.putIfAbsent("apple", 10);
map.putIfAbsent("apple", 20); // 기존 값 유지

System.out.println(map.get("apple")); // 10
```

**computeIfAbsent() — Key 없을 때 값 생성**

```java
Map<String, List<String>> map = new HashMap<>();

// [Map] Key가 없으면 List 생성·저장 후 반환, 있으면 기존 List 반환
map.computeIfAbsent("aet", k -> new ArrayList<>()).add("eat");
map.computeIfAbsent("aet", k -> new ArrayList<>()).add("tea");

// {aet=[eat, tea]}
```

- `k`: 전달받은 Key
- `k -> new ArrayList<>()`: Key가 없을 때 실행되는 람다식
- Key가 없으면 새 List를 생성해 저장하고 반환
- Key가 있으면 기존 List 반환
- 반환된 List에 `.add()` 실행

### 5.2. getOrDefault() — 빈도수 계산

**Java**

```java
map.put(str, map.getOrDefault(str, 0) + 1);
```

**C++**

```cpp
m[str]++;
```

### 5.3. keySet() vs values() vs entrySet()

| API          | 반환형                | 설명                   |
| ------------ | --------------------- | ---------------------- |
| `keySet()`   | `Set<K>`              | 모든 Key 반환          |
| `values()`   | `Collection<V>`       | 모든 Value 반환        |
| `entrySet()` | `Set<Map.Entry<K,V>>` | 모든 Key-Value 쌍 반환 |

**values() 활용 — Value 목록으로 List 생성**

```java
Map<String, List<String>> group = new HashMap<>();

group.put("aet", new ArrayList<>(List.of("eat", "tea")));
group.put("ant", new ArrayList<>(List.of("tan", "nat")));

List<List<String>> answer = new ArrayList<>(group.values());
```

> `values()`는 `Collection<V>`를 반환하며, `new ArrayList<>(...)`로 별도의 List를 생성할 수 있다. `HashMap`은 순서를 보장하지 않는다.

### 5.4. C++ insert() vs operator[]

| 기능     | `insert({key, value})` | `m[key] = value` |
| -------- | ---------------------- | ---------------- |
| Key 없음 | 삽입                   | 삽입             |
| Key 있음 | 기존 값 유지           | 값 덮어쓰기      |

```cpp
map<string, int> m;

m.insert({"apple", 10});
m.insert({"apple", 20}); // 기존 값 유지
m["apple"] = 30;         // 기존 값 수정

cout << m["apple"];      // 30
```

`insert()`의 반환형은 `pair<iterator, bool>`이다.

```cpp
auto result = m.insert({"banana", 20});

result.first->first;  // Key
result.first->second; // Value
result.second;        // 삽입 성공 여부 (bool)
```

### 5.5. C++ find() vs count()

```cpp
map<string, int> m;
m["apple"] = 10;

m.count("apple");  // 1 (존재)
m.count("banana"); // 0 (없음)

auto it = m.find("apple");

if (it != m.end()) {
    cout << it->first;  // Key
    cout << it->second; // Value
}
```

| API          | 반환형     | 설명                                     |
| ------------ | ---------- | ---------------------------------------- |
| `count(key)` | `size_t`   | Key 존재 여부 (0 또는 1)                 |
| `find(key)`  | `iterator` | Key에 해당하는 원소 위치, 없으면 `end()` |

> C++ `m[key]`는 Key가 없으면 기본값을 삽입하므로 단순 조회 시 주의한다.

---

## Reference

- [Java 11 Map API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html)
- [C++ std::map](https://en.cppreference.com/w/cpp/container/map)
- [C++ std::unordered_map](https://en.cppreference.com/w/cpp/container/unordered_map)
