# StringBuilder API — Java

> Java 11 기준  
> `n`: 현재 문자열 길이 / `k`: 추가·삭제·반환되는 문자열 길이 / `m`: 검색할 문자열 길이

## 1. StringBuilder를 사용하는 이유

### 1.1. String은 불변(Immutable)

`String`은 한 번 만들어지면 내용을 바꿀 수 없다. `+=`로 문자를 이어 붙이면 **매번 새 String 객체를 만들고 기존 내용을 전부 복사**한다.

```java
String s = "";
for (int i = 0; i < n; i++) {
    s += 'a'; // 매번 길이 i짜리 문자열 복사 → 전체 O(n²)
}
```

### 1.2. StringBuilder는 가변(Mutable)

`StringBuilder`는 내부 `char` 버퍼를 직접 수정한다. 버퍼가 꽉 찰 때만 약 2배로 늘리며 복사하므로, `append()` 한 번은 **평균 O(1)** 이다.

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < n; i++) {
    sb.append('a'); // 전체 O(n)
}
String s = sb.toString();
```

| 방식                    | 문자 n개 이어 붙이기 | 비고                                |
| ----------------------- | -------------------- | ----------------------------------- |
| `String +=`             | O(n²)                | 매번 새 객체 생성·복사              |
| `StringBuilder.append`  | O(n)                 | 버퍼 재사용                         |
| `StringBuffer.append`   | O(n)                 | 동기화(synchronized)로 상대적으로 느림 |

> 코딩테스트처럼 단일 스레드 환경에서는 `StringBuffer` 대신 `StringBuilder`를 사용한다.

### 1.3. 주로 쓰는 상황

- 반복문에서 문자열을 누적할 때 (출력 결과 모으기 포함)
- 문자열을 스택처럼 다룰 때 (끝에 추가·끝에서 삭제)
- 문자열 뒤집기, 특정 문자 수정이 필요할 때

---

## 2. 기본 API

```java
StringBuilder sb = new StringBuilder("abc");
```

> 각 예시는 **`"abc"`에서 독립적으로 실행**한 결과다.

### 2.1. 생성

| 기능              | API                                 | 시간복잡도 |
| ----------------- | ----------------------------------- | ---------- |
| 빈 객체 생성      | `new StringBuilder()`               | O(1)       |
| 문자열로 생성     | `new StringBuilder(String str)`     | O(k)       |
| 초기 용량 지정    | `new StringBuilder(int capacity)`   | O(capacity) |

### 2.2. 추가·삽입

| 기능             | API                                          | 예시 → 결과                         | 시간복잡도       |
| ---------------- | -------------------------------------------- | ----------------------------------- | ---------------- |
| 끝에 추가        | `StringBuilder append(value)`                | `sb.append("de")` → `"abcde"`       | 평균 O(k)        |
| 특정 위치에 삽입 | `StringBuilder insert(int offset, value)`    | `sb.insert(1, "X")` → `"aXbc"`      | O(n + k)         |

> `append()`, `insert()`는 `char`, `int`, `String`, `char[]` 등 대부분의 자료형을 받는다.

### 2.3. 조회

| 기능             | API                                   | 예시 → 결과                  | 시간복잡도 |
| ---------------- | ------------------------------------- | ---------------------------- | ---------- |
| 문자 조회        | `char charAt(int index)`              | `sb.charAt(0)` → `'a'`       | O(1)       |
| 길이 조회        | `int length()`                        | `sb.length()` → `3`          | O(1)       |
| 부분 문자열      | `String substring(int start, int end)` | `sb.substring(1, 3)` → `"bc"` | O(k)       |
| 끝까지 부분 문자열 | `String substring(int start)`       | `sb.substring(1)` → `"bc"`   | O(k)       |

### 2.4. 수정·삭제

| 기능           | API                                                    | 예시 → 결과                           | 시간복잡도 |
| -------------- | ------------------------------------------------------ | ------------------------------------- | ---------- |
| 문자 수정      | `void setCharAt(int index, char ch)`                   | `sb.setCharAt(1, 'X')` → `"aXc"`      | O(1)       |
| 문자 하나 삭제 | `StringBuilder deleteCharAt(int index)`                | `sb.deleteCharAt(1)` → `"ac"`         | O(n)       |
| 범위 삭제      | `StringBuilder delete(int start, int end)`             | `sb.delete(0, 2)` → `"c"`             | O(n)       |
| 범위 교체      | `StringBuilder replace(int start, int end, String str)` | `sb.replace(0, 2, "XY")` → `"XYc"`    | O(n + k)   |
| 뒤집기         | `StringBuilder reverse()`                              | `sb.reverse()` → `"cba"`              | O(n)       |
| 길이 변경      | `void setLength(int newLength)`                        | `sb.setLength(2)` → `"ab"`            | 줄이기 O(1) / 늘리기 O(k) |

> - 삭제·삽입은 뒤쪽 문자들을 당기거나 미는 작업이므로 정확히는 **O(n - index)** 다. 즉 **끝에서의 삭제는 O(1)**, 앞쪽 삭제는 O(n)이다.
> - `setLength()`로 길이를 늘리면 늘어난 부분은 `'\u0000'`으로 채워진다.

### 2.5. 검색

| 기능             | API                              | 예시 → 결과                   | 시간복잡도 |
| ---------------- | -------------------------------- | ----------------------------- | ---------- |
| 첫 일치 위치     | `int indexOf(String str)`        | `sb.indexOf("bc")` → `1`      | O(n · m)   |
| 마지막 일치 위치 | `int lastIndexOf(String str)`    | `sb.lastIndexOf("a")` → `0`   | O(n · m)   |

> 검색 결과가 없으면 `-1`을 반환한다. 검색 인자는 `String`만 받으므로 문자는 `String.valueOf(ch)`로 변환한다.

### 2.6. 변환·비교

| 기능          | API                                    | 예시 → 결과                         | 시간복잡도 |
| ------------- | -------------------------------------- | ----------------------------------- | ---------- |
| 문자열 변환   | `String toString()`                    | `sb.toString()` → `"abc"`           | O(n)       |
| 사전순 비교   | `int compareTo(StringBuilder another)` | `sb.compareTo(new StringBuilder("abd"))` → 음수 | O(n)       |

> - 범위를 받는 API(`substring`, `delete`, `replace`)의 **`end`는 제외**된다.
> - `compareTo()`는 Java 11부터 지원한다.

---

## 3. 자주 쓰는 패턴

```java
// 첫 문자 / 마지막 문자
sb.charAt(0);
sb.charAt(sb.length() - 1);

// 마지막 문자 삭제 — O(1)
sb.deleteCharAt(sb.length() - 1);
// 또는
sb.setLength(sb.length() - 1);

// 전체 비우기 — O(1)
sb.setLength(0);

// 비어 있는지 확인 — Java 11 (isEmpty()는 Java 15부터)
sb.length() == 0;

// 문자열 내용 비교
sb.toString().equals("abc");

// 회문(팰린드롬) 확인
String s = "level";
new StringBuilder(s).reverse().toString().equals(s);
```

**스택처럼 사용하기**

```java
// 인접한 같은 문자 제거: "abbaca" → "ca"
StringBuilder sb = new StringBuilder();

for (char c : s.toCharArray()) {
    int last = sb.length() - 1;

    if (last >= 0 && sb.charAt(last) == c) {
        sb.deleteCharAt(last); // pop
    } else {
        sb.append(c);          // push
    }
}
```

---

## 4. 주의

> - `substring()`은 원본을 수정하지 않고 새 `String`을 반환한다.
> - `sb.equals()`는 문자열 내용이 아닌 **객체 참조**를 비교한다. 내용 비교는 `toString().equals()` 또는 `compareTo() == 0`을 사용한다.
> - 앞쪽 문자를 삭제·삽입하면 뒤의 문자들을 이동시키므로 **O(n)** 이다. 반복문 안에서 앞쪽을 수정하면 전체 O(n²)이 될 수 있다.
> - `append()`, `insert()`, `delete()`, `reverse()` 등은 **자기 자신을 반환**하므로 메서드 체이닝이 가능하다.
>
>   ```java
>   sb.append("d").reverse().deleteCharAt(0); // "abc" → "abcd" → "dcba" → "cba"
>   ```

---

## Reference

- [Java 11 StringBuilder API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/StringBuilder.html)
