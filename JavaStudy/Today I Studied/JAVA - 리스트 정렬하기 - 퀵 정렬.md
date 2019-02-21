## JAVA - 리스트 정렬하기 - 퀵 정렬

#### 퀵 정렬(quick sort)

- 재귀적(recursive)이다.
- 오름차순을 기준으로 한다.
- 기준 값(pivot)을 중심으로 왼쪽 부분집합과 오른쪽 부분집합을 분할한다.
- 왼쪽은 pivot보다 작은 값으로 구성한다.
- 오른쪽은 pivot보다 큰 값으로 구성한다.
- 분할된 부분집합의 크기가 0이나 1이 될 때까지 재귀를 활용하여 반복한다.



#### 퀵 정렬 예시

- 4, 1, 3, -1, -5 을 퀵 정렬로 정렬해보자.
- 중앙값인 3을 pivot으로 삼고 맨 왼쪽 값과 맨 오른쪽 값을 비교해본다.
- 왼쪽 값인 4는 3보다 크기 때문에 hold해두고 오른쪽 값인 -5도 3보다 작기 때문에 hold한다.
- 4와 -5를 교환한다. (-5, 1, 3, -1, 4)
- -5와 4는 3과의 비교에서 조건에 부합하기 때문에 왼쪽과 오른쪽 값을 하나씩 이동한다.
- 왼쪽 값인 1은 3보다 작기 때문에 하나 더 이동한다.
- 왼쪽 값이 pivot과 동일하므로 왼쪽 값을 3으로 hold한다.
- 오른쪽 값인 -1은 3보다 작으므로 hold된 왼쪽 값과 교환한다. (-5, 1, -1, 3 ,4)
- 왼쪽 값이 -1이 되었고 3보다 작으므로 다시 왼쪽 값을 이동시켜 3으로 hold한다.
- 오른쪽 값 역시 3이므로 hold하고 왼쪽과 오른쪽이 만났으므로 pivot인 3을 기준으로 왼쪽집합과 오른쪽집합을 나눈다 ( (-5, 1, -1) , 3 ,(4))
- 왼쪽 집합에서 다시 중앙값인 1을 pivot으로 삼고 왼쪽, 오른쪽 값을 비교해본다.
- -5가 1보다 작으므로 왼쪽값은 이동하고, 1이 되었으므로 hold한다.
- 오른쪽 값인 -1은 1보다 작으므로 hold된 왼쪽값인 1과 교환한다. (-5, -1, 1)
- 왼쪽값이 이동하여 오른쪽값과 만났으므로 1을 기준으로 왼쪽, 오른쪽 부분집합을 나눈다. ((-5, -1) , 1 ,() )
- 왼쪽 부분집합에서 새로운 pivot을 -5로 잡고 확인하면 이미 정렬이 된 상태이다.
- 오른쪽 부분 집합은 공집합이므로 넘어간다.
- 다시 처음 pivot인 3의 오른쪽 부분 집합을 확인하고 길이가 1이므로 넘어간다.
- (-5, -1 ,1 ,3 ,4) 로 퀵 정렬이 마무리 된다.



#### 퀵 정렬 예시 코드

```java
	static int i = 1;

	public static void quickSort(int[] arr, int start, int end) {
		if (start < end) {
			int p = partition(arr, start, end);
			quickSort(arr, start, p - 1);
			quickSort(arr, p + 1, end);
		}

	}

	public static int partition(int[] arr, int start, int end) {
		int left = start;
		int right = end;
		int pivot = arr[(left + right) / 2];

		System.out.println("퀵 정렬 " + i++ + "단계: pivot: " + pivot);

		while (left < right) {
			while ((arr[left] < pivot) && (left < right)) {
				left++;
			}
			while ((arr[right] > pivot) && (left < right)) {
				right--;
			}
			if (left < right) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;

			}
		}

		return left;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 1, 3, -1, -5 };
		quickSort(arr, 0, arr.length - 1);
		for (int i : arr) {
			System.out.printf("%d ", i);
		}
	}
```

```java
결과 : 
퀵 정렬 1단계: pivot: 3
퀵 정렬 2단계: pivot: 1
퀵 정렬 3단계: pivot: -5
-5 -1 1 3 4 
```



#### 퀵 정렬의 특징

- 복잡하지만 효율적인 정렬 방법이다.
- 분할 정복(divide-and-conquer) 알고리즘의 하나로, 평균적으로 매우 빠른 속도를 자랑한다.
- 분할 정복 방법이란
  - 문제를 작은 2문제로 분할하고, 각각을 해결한 다음, 결과를 모아서 원래의 문제를 해결하는 방법이다.
  - 분할 정복은 대개 재귀적으로 구현한다.
- 합병 정렬(merge sort)와는 다르게 리스트를 불균등하게 분할한다.
- 퀵 정렬의 시간 복잡도
  - 최선의 경우 O(nlogn), 최악의 경우 O(n<sup>2</sup>)이며 평균적으로 O(nlogn)의 복잡도를 가진다.
  - 다른 O(nlogn)의 복잡도를 가지는 정렬보다도 속도가 빠르다.
  - 불필요한 데이터의 이동을 줄이고, 먼거리의 데이터를 교환함 뿐만 아니라 한 번 결정된 pivot이 추후의 계산에는 제외되기 때문이다.