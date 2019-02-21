## JAVA - 리스트 정렬하기 - 합병 정렬

#### 합병 정렬(merge sort)

- 오름차순을 기준으로 정렬한다.
- 재귀적(recursive)이다.
- 리스트의 길이가 0 또는 1이면 정렬이 된 것으로 판단한다.
- 정렬되지 않은 리스트를 절반으로 잘라 비슷한 크기의 두 리스트로 나눈다.
- 각 부분의 리스트를 합병 정렬을 통해 정렬한다. (재귀함수)
- 정렬된 두 리스트를 다시 하나의 정렬된 리스트로 합병한다.
- 추가적인 리스트가 필요하다.
- 합병 정렬에서 실제로 정렬이 이루어지는 시점은 2개의 리스트를 **합병**하는 단계이다.
- 합병 단계에서 각 리스트에 포인터를 두고 비교하면서 추가된 리스트에 정렬순 저장한다.
- 병합이 완료되면 추가리스트에서 원본리스트로 다시 복사한다.



#### 합병 정렬 예시

- 31, 4, 3, 1, -1, -5, 22, -10으로 구성된 배열을 합병 정렬로 정렬해 보자.
- [31 4 3 1] | [-1 -5 22 -10] 으로 리스트를 나눈다.
- [31 4] | [3 1] | [-1 -5 22 -10] 으로 리스트를 나눈다.
- [31] | [4] | [3 1] | [-1 -5 22 -10] 으로 리스트를 나누고 길이가 1인 리스트가 생성이 되었으므로 합병한다.
- 31 과 4를 정렬하고 [4 31] | [3 1] | [-1 -5 22 -10] 으로 합병한다.
- 두번째 길이가 2인 리스트를 나눈후 합병 정렬하면 [4 31] | [1 3] | [-1 -5 22 -10] 이 된다.
- 길이가 2인 두 리스트를 합병 정렬한다. 각 리스트의 왼쪽값을 포인터로 지정하고 비교한다.
- 1과 4를 비교하고 1이 작으므로 임시리스트 첫칸에 1을 입력한다.
- 1을 나타내던 포인터를 이동하고 다시 비교한다.
- 이와 같은 과정으로 두 리스트중 포인터가 리스트 끝까지 도착하게되면 남은 리스트의 값을 순서대로 임시리스트에 저장한다.
- 임시 리스트에 저장된 값을 원본리스트로 복사한다.
- [1 3 4 31] | [-1 -5 22 -10] 으로 완성되며 남은 우측 길이가 4인 리스트역시 정렬하게 되면
- [1 3 4 31] | [-10 -5 -1 22] 로 완성된다.
- 마지막 두 리스트를 합병하고 [-10 -5 -1 1 3 4 22 31] 로 오름차순 정렬이 완료된다.



#### 합병 정렬 예시 코드

```java
	public static int[] sorted;

	public static void mergeSort(int[] arr, int left, int right) {
		int mid;
		if (left < right) {
			mid = (left + right) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	public static void merge(int[] arr, int left, int mid, int right) {
		int leftPointer, rightPointer, sortedIndex, tempIndex;
		leftPointer = left;
		rightPointer = mid + 1;
		sortedIndex = left;

		while (leftPointer <= mid && rightPointer <= right) {
			if (arr[leftPointer] <= arr[rightPointer]) {
				sorted[sortedIndex++] = arr[leftPointer++];
			} else {
				sorted[sortedIndex++] = arr[rightPointer++];
			}

		}

		if (leftPointer > mid) {
			for (tempIndex = rightPointer; tempIndex <= right; tempIndex++) {
				sorted[sortedIndex++] = arr[tempIndex];
			}
		} else {
			for (tempIndex = leftPointer; tempIndex <= mid; tempIndex++) {
				sorted[sortedIndex++] = arr[tempIndex];
			}

		}

		for (tempIndex = left; tempIndex <= right; tempIndex++) {
			arr[tempIndex] = sorted[tempIndex];
		}

	}

	public static void main(String[] args) {
		int[] arr = { 31, 4, 3, 1, -1, -5, 22, -10 };
		sorted = new int[arr.length];
		mergeSort(arr, 0, arr.length - 1);
		for (int item : arr) {
			System.out.printf("%d ", item);
		}

	}
```

```java
결과 : -10 -5 -1 1 3 4 22 31 
```



#### 합병 정렬의 특징

- 복잡하지만 효율적인 정렬 방법이다.
- 분할 정복(divide-and-conquer) 알고리즘의 하나다.
- 데이터의 분포에 영향을 덜 받는다. 즉, 정렬되는 시간이 동일하다.
- 레코드를 배열(Array)로 구성하면 임시 배열이 필요하다.
  - 제자리 정렬(in-place sorting)이 아니다.
  - 레코드의 크기가 클 수록 이동 횟수가 늘어난다.
- 레코드를 연결리스트(Linked List)로 구성하면 링크 인덱스만 변경되므로 데이터의 이동이 매우 작아진다.
  - 제자리 정렬로 구현 가능하다.
  - 크기가 큰 레코드 일수록 연결리스트를 사용하면 퀵 정렬을 포함한 어떤 정렬보다 합병 정렬이 효율적이다.
- 합병 정렬의 시간복잡도
  - 분할 단계에선 연산이 수행되지 않고 합병 단계에서 수행된다.
  - T(n) = nlogn(비교) + 2nlogn(이동) = 3nlogn = O(nlogn)