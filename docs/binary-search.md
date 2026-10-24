# 이분 탐색 (Binary Search) 코딩테스트 정리 — Java 11

이분 탐색은 중간값을 확인하고 **정답이 존재할 수 없는 절반을 버려** 탐색 범위를 줄이는 알고리즘이다.

- 시간복잡도: **O(log N)**
- 공간복잡도: **O(1)** — 반복문 구현 기준
- 전제조건: 탐색 기준에 따라 정렬되어 있거나, 조건이 한 방향으로 바뀌는 구조여야 한다.

> 아래 구현은 모두 **오름차순으로 정렬된 `int[] nums`** 기준이다. `n`은 배열 길이를 뜻한다. 파라메트릭 서치는 별도로 다룬다.

## 1. 이분 탐색의 세 가지 유형

| 유형 | 목적 | 핵심 |
| --- | --- | --- |
| Binary Search | target과 일치하는 인덱스 하나 | `== target`이면 종료 |
| Lower Bound | target 이상인 첫 인덱스 | `< target`인 왼쪽 구간을 버림 |
| Upper Bound | target 초과인 첫 인덱스 | `<= target`인 왼쪽 구간을 버림 |

예를 들어 `target = 2`일 때:

```text
인덱스:  0  1  2  3  4  5  6
값:     [1, 2, 2, 2, 4, 6, 8]
            ↑        ↑
           lb = 1   ub = 4
```

- Binary Search: `1`, `2`, `3` 중 하나를 반환한다.
- Lower Bound: 같은 값들이 시작하는 위치인 `1`을 반환한다.
- Upper Bound: 같은 값들이 끝난 직후의 위치인 `4`를 반환한다.

**Lower/Upper Bound는 target이 없어도 위치를 반환한다.** 위 배열에서 target이 `3`이면 둘 다 `4`, target이 `9`이면 둘 다 배열 길이인 `7`이다.

## 2. 탐색 범위와 종료 조건

구간에 실제 탐색할 원소가 남아 있는 동안 반복한다. 초기값, 반복 조건, 범위 갱신을 한 세트로 맞춘다.

| 구분 | 닫힌 구간 `[left, right]` | 반열린 구간 `[left, right)` |
| --- | --- | --- |
| `left` 초기값 | `0` | `0` |
| `right` 초기값 | `n - 1` | `n` |
| 반복 조건 | `left <= right` | `left < right` |
| 오른쪽 탐색 | `left = mid + 1` | `left = mid + 1` |
| 왼쪽 탐색 | `right = mid - 1` | `right = mid` |

위 표는 `mid`를 다음 탐색 구간에서 제외하는 구현 기준이다.

- `[left, right]`: `left == right`이면 원소 하나가 남아 있으므로 마지막 원소까지 검사한다.
- `[left, right)`: `right`를 포함하지 않으므로 `left == right`이면 빈 구간이다. `right = mid`로 바꾸면 `mid`도 다음 탐색에서 제외된다.

> `left < right`를 쓰는 모든 이분 탐색이 반열린 구간인 것은 아니다. 여기서는 위 두 구현 방식을 비교한다. 이후 코드는 **닫힌 구간으로 통일**한다.

## 3. Java 구현 — 닫힌 구간 기준

### 3.1. Binary Search: target 찾기

```java
static int binarySearch(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return -1;
}
```

일치하면 바로 반환하고, 없으면 `-1`을 반환한다. 중복된 target이 있으면 첫 번째나 마지막 인덱스를 보장하지 않는다.

### 3.2. Lower Bound: target 이상인 첫 위치

```java
static int lowerBound(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return left;
}
```

- `nums[mid] < target`: `mid`와 그 왼쪽은 모두 target 미만이므로 버린다.
- `nums[mid] >= target`: `mid`는 조건을 만족하지만, 더 왼쪽에도 만족하는 값이 있을 수 있으므로 왼쪽을 탐색한다.

`mid`를 다음 탐색에서 제외해도 되는 이유는 이미 조건을 만족하는 위치임을 확인했기 때문이다. 이 구현은 별도의 `answer` 없이 종료 후 `left`로 경계를 구한다.

### 3.3. Upper Bound: target 초과인 첫 위치

```java
static int upperBound(int[] nums, int target) {
    int left = 0;
    int right = nums.length - 1;

    while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums[mid] <= target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return left;
}
```

Lower Bound와의 차이는 **`<`와 `<=` 하나**다. Upper Bound는 같은 값까지 지나쳐 target보다 큰 값이 시작하는 위치를 찾는다.

## 4. Lower/Upper Bound가 `left`를 반환하는 이유

두 구현 모두 조건을 만족하지 않는 값들은 왼쪽에, 만족하는 값들은 오른쪽에 모여 있다.

| 유형 | 조건을 만족하지 않는 값 | 조건을 만족하는 값 |
| --- | --- | --- |
| Lower Bound | `< target` | `>= target` |
| Upper Bound | `<= target` | `> target` |

탐색하는 동안 다음 상태를 유지한다.

- `left`보다 앞에 있는 원소들은 모두 조건을 만족하지 않는다.
- `right`보다 뒤에 있는 원소들은 모두 조건을 만족한다.
- `[left, right]`는 아직 확인해야 할 구간이다.

종료 시 `left == right + 1`이 되어 미확인 구간이 사라진다. 따라서 **`left`가 조건을 만족하는 첫 위치**다.

```text
조건 불만족 구간 | 조건 만족 구간
           right  left
```

- 모두 만족하면 `right == -1`, `left == 0`이다.
- 모두 불만족하면 `right == n - 1`, `left == n`이다.
- 빈 배열이면 반복문을 실행하지 않고 `0`을 반환한다.

`right`와 `left`는 각각 `-1`, `n`일 수 있으므로 항상 실제 원소의 인덱스인 것은 아니다. 일반 Binary Search는 일치 여부를 반환해야 하므로 이 반환 규칙을 그대로 적용하지 않는다.

## 5. 코딩테스트 응용

`lb = lowerBound(nums, target)`, `ub = upperBound(nums, target)`으로 두면:

| 구하려는 것 | 계산 |
| --- | --- |
| target 존재 여부 | `lb < ub` 또는 `lb < n && nums[lb] == target` |
| target의 첫 번째 인덱스 | 존재하면 `lb`, 아니면 `-1` |
| target의 마지막 인덱스 | 존재하면 `ub - 1`, 아니면 `-1` |
| target 등장 횟수 | `ub - lb` |
| target 미만인 원소 개수 | `lb` |
| target 이하인 원소 개수 | `ub` |
| target 이상인 원소 개수 | `n - lb` |
| target 초과인 원소 개수 | `n - ub` |
| `[A, B]` 범위의 원소 개수 | `upperBound(nums, B) - lowerBound(nums, A)` (`A <= B`) |
| 정렬 순서를 유지하는 삽입 위치 | `lb`: 같은 값들 앞 / `ub`: 같은 값들 뒤 |

### 5.1. 중복된 target의 첫 번째·마지막 인덱스

첫·마지막 인덱스 찾기는 **Lower/Upper Bound의 활용 유형**이다.

```java
int[] nums = {1, 2, 2, 2, 4, 6, 8};
int target = 2;

int lb = lowerBound(nums, target); // 1
int ub = upperBound(nums, target); // 4

int first = lb < ub ? lb : -1;     // 1
int last = lb < ub ? ub - 1 : -1; // 3
int count = ub - lb;              // 3
```

target이 없으면 `lb == ub`이다. 위 배열에서 target이 `3`이면 `lb = ub = 4`이므로 첫·마지막 인덱스는 모두 `-1`, 개수는 `0`이다.

### 5.2. 값 범위에 속하는 원소 개수

```java
int[] nums = {1, 2, 2, 2, 4, 6, 8};
int a = 2;
int b = 6;

int count = upperBound(nums, b) - lowerBound(nums, a); // 6 - 1 = 5
```

`[2, 6]`에 속하는 값은 `2, 2, 2, 4, 6`으로 총 5개다.

## 6. 실수하기 쉬운 부분

### 6.1. 중간값 오버플로 방지

```java
int mid = left + (right - left) / 2;
```

`(left + right) / 2`는 덧셈 중 `int` 범위를 넘을 수 있다. 위 방식은 이 문서처럼 음수가 아닌 배열 인덱스 범위에서 안전하다.

### 6.2. 탐색 범위를 반드시 줄이기

위 닫힌 구간 구현에서는 `mid + 1`, `mid - 1`로 갱신한다. 이를 `left = mid` 등으로 바꾸면 범위가 줄지 않아 무한 반복할 수 있다.

### 6.3. 배열 끝과 target 존재 여부 확인

Lower/Upper Bound의 반환값은 `n`일 수 있으므로 배열 접근 전에 범위를 확인한다.

```java
int lb = lowerBound(nums, target);
boolean exists = lb < nums.length && nums[lb] == target;
```

`lb < n`이어도 `nums[lb]`가 target보다 클 수 있다. 첫·마지막 일치 인덱스가 필요하면 target의 존재 여부까지 확인한다.

### 6.4. 정렬 비용과 인덱스 변화 고려

정렬부터 해야 한다면 일반적으로 O(N log N)의 정렬 비용이 추가된다. 단 한 번 값을 찾는 문제라면 O(N) 선형 탐색이 더 나을 수 있다.

정렬하면 원래 인덱스도 달라질 수 있다. 원본 인덱스가 필요하면 값과 인덱스를 함께 저장하는 등의 처리가 필요하다.

삽입 위치를 O(log N)에 구해도 배열 중간에 실제로 삽입하려면 원소 이동에 O(N)이 걸릴 수 있다.

### 6.5. 내림차순이면 비교 방향 재검토

위 코드는 모두 오름차순 기준이다. 내림차순 배열에서는 비교 결과에 따라 어느 절반을 버릴 수 있는지 다시 판단한다.

## 7. Java에서 제공하는 이분 탐색 API

```java
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

int[] nums = {1, 2, 2, 2, 4};
int index = Arrays.binarySearch(nums, 2); // 일치하는 인덱스 중 하나

int result = Arrays.binarySearch(nums, 3); // -5
int insertionPoint = -result - 1;          // 4: result가 음수일 때만 계산

List<Integer> list = Arrays.asList(1, 2, 2, 2, 4);
int listIndex = Collections.binarySearch(list, 2);
```

- 검색 기준에 맞게 미리 정렬되어 있어야 한다.
- 값이 있으면 일치하는 인덱스 하나를 반환한다. 중복 값의 첫·마지막 위치는 보장하지 않는다.
- 값이 없으면 `-(삽입 위치) - 1`을 반환한다.
- Java 11의 `Arrays`와 `Collections`에는 별도의 `lowerBound()`, `upperBound()` 메서드가 없다.
- `Collections.binarySearch()`는 `ArrayList`처럼 임의 접근이 가능한 리스트에서 O(log N)이다. 큰 연결 리스트에서는 원소 접근을 위한 이동 비용이 O(N)일 수 있다.

## 8. 문제를 읽고 적용할 방식 판단하기

| 문제의 요구 | 적용할 방식 |
| --- | --- |
| target의 존재 여부 또는 일치하는 인덱스 하나 | Binary Search |
| target 이상인 첫 위치 | Lower Bound |
| target 초과인 첫 위치 | Upper Bound |
| target의 첫 일치 인덱스 | Lower Bound 후 존재 여부 확인 |
| target의 마지막 일치 인덱스 | Upper Bound에서 1을 뺀 뒤 존재 여부 확인 |
| target의 등장 횟수 또는 특정 값 범위의 원소 개수 | Lower Bound와 Upper Bound 조합 |

## 9. 암기용 요약

아래는 모두 `left = 0`, `right = n - 1`, `while (left <= right)` 기준이다.

```java
// Binary Search: 반복문 안
if (nums[mid] == target) return mid;
else if (nums[mid] < target) left = mid + 1;
else right = mid - 1;
// 반복문 종료 후: return -1;

// Lower Bound: 반복문 안
if (nums[mid] < target) left = mid + 1;
else right = mid - 1;
// 반복문 종료 후: return left;

// Upper Bound: 반복문 안
if (nums[mid] <= target) left = mid + 1;
else right = mid - 1;
// 반복문 종료 후: return left;
```

핵심은 **현재 `mid`를 기준으로 어느 절반에 정답이 존재할 수 없는지 판단하는 것**이다. 기본 탐색과 두 경계를 이해하면 첫·마지막 위치, 개수, 삽입 위치를 같은 원리로 해결할 수 있다.

---

## Reference

- [정리의 바탕이 된 공유 대화](https://chatgpt.com/share/6adc288e-5bb4-83ee-8ff0-0bfb2ddf24b3)
- [관련 풀이: LeetCode 704. Binary Search](../LeetCode/0704-binary-search/README.md)
