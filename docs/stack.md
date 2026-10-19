# Stack API — Java vs C++

> Java 11 / C++11 기준  
> `E`: Java 원소 자료형 / `T`: C++ 원소 자료형

## 1. 자료구조 비교

| Java                       | C++        | 순서            | 삽입·삭제·조회 |
| -------------------------- | ---------- | --------------- | -------------- |
| `Deque<E>` (`ArrayDeque`)  | `stack<T>` | LIFO (후입선출) | O(1)           |

## 2. 기본 API

| 기능        | Java                | C++                     | 시간복잡도 |
| ----------- | ------------------- | ----------------------- | ---------- |
| 삽입        | `void push(E e)`    | `void push(const T& x)` | 평균 O(1)  |
| top 조회    | `E peek()`          | `T& top()`              | O(1)       |
| top 제거    | `E pop()`           | `void pop()`            | O(1)       |
| 비어 있는지 | `boolean isEmpty()` | `bool empty()`          | O(1)       |
| 크기        | `int size()`        | `size_t size()`         | O(1)       |
| 전체 삭제   | `void clear()`      | -                       | O(n)       |

> **주의**
>
> - Java `push()`는 내부 배열이 꽉 차면 크기를 늘리며 복사하므로 평균 O(1)이다.
> - Java `pop()`은 제거한 값을 반환하지만, C++ `pop()`은 반환값이 없다.
> - Java `ArrayDeque`는 `null`을 저장할 수 없다.
> - C++ `stack`에는 `clear()`가 없으며, `stk = stack<T>();`로 비운다.

## 3. Stack 순회

### Java

```java
for (int x : stk) {
    // top → bottom 순서
}
```

### C++11

```cpp
while (!stk.empty()) {
    int x = stk.top(); // top → bottom 순서
    stk.pop();
}
```

> C++ `stack`은 반복자(iterator)를 제공하지 않으므로 원소를 꺼내며 순회한다.

## 4. Stack 비교

| 기능           | Java | C++            |
| -------------- | ---- | -------------- |
| 전체 원소 비교 | -    | `stk1 == stk2` |

Java `ArrayDeque`는 `equals()`를 재정의하지 않아 객체 참조를 비교한다.

---

## 5. 주요 API 예제

### 5.1. Java pop() vs C++ pop()

| API          | 동작           | 반환값      |
| ------------ | -------------- | ----------- |
| Java `pop()` | top 제거       | 제거한 원소 |
| C++ `pop()`  | top 제거       | 없음        |

**Java**

```java
Deque<Integer> stk = new ArrayDeque<>();

stk.push(1);
stk.push(2);

int x = stk.pop(); // 2
```

**C++**

```cpp
stack<int> stk;

stk.push(1);
stk.push(2);

int x = stk.top(); // 2
stk.pop();
```

### 5.2. 빈 스택 접근

| API           | 결과                             |
| ------------- | -------------------------------- |
| Java `peek()` | `null` 반환                      |
| Java `pop()`  | `NoSuchElementException` 발생    |
| C++ `top()`   | 정의되지 않은 동작 (UB)          |
| C++ `pop()`   | 정의되지 않은 동작 (UB)          |

> C++에서는 `top()`, `pop()` 호출 전 반드시 `empty()`로 확인한다.

### 5.3. Java Stack vs ArrayDeque

| 클래스       | 구조               | 동기화 | 권장 |
| ------------ | ------------------ | ------ | ---- |
| `Stack`      | `Vector` 상속      | O      | X    |
| `ArrayDeque` | 가변 크기 원형 배열 | X      | O    |

> `Stack`은 레거시 클래스로 동기화 비용 때문에 느리다. Java 공식 문서도 `Deque` 사용을 권장한다.

### 5.4. ArrayDeque 스택 API vs Deque API

| 스택 API | 대응 Deque API  | 위치  |
| -------- | --------------- | ----- |
| `push()` | `addFirst()`    | 앞쪽  |
| `pop()`  | `removeFirst()` | 앞쪽  |
| `peek()` | `peekFirst()`   | 앞쪽  |

```java
Deque<Integer> stk = new ArrayDeque<>();

stk.push(1);
stk.push(2);
stk.push(3);

System.out.println(stk); // [3, 2, 1] — 앞쪽이 top
```

> 스택으로 사용할 때는 `push()`, `pop()`, `peek()`만 사용한다. `addLast()`, `offerLast()` 등을 섞으면 bottom 쪽을 조작하게 된다.

---

## Reference

- [Java 11 ArrayDeque API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/ArrayDeque.html)
- [Java 11 Deque API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Deque.html)
- [C++ std::stack](https://en.cppreference.com/w/cpp/container/stack)
