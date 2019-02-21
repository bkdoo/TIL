## JAVA - 리스트 검색하기 - 이진 검색

#### 이진 검색(binary search)

- 리스트가 정렬(오름차순)되어 있는 상태에서 찾고자 하는 값을 검색할 때 효율적인 방법이다.
- 정렬된 리스트의 속성을 이용한다. 즉, 많은 원소를 일일이 비교해보지 않고도 원하는 값을 검색할 수 있다.
- 검색이 반복될 때마다 목표값을 찾을 확률은 두 배가 되므로 속도가 빠르다.
- 검색 범위의 길이가 1이하(코딩 방법에 따라 1미만이 될 수 있음)이 되면 값이 없음을 리턴한다.



#### 이진 검색 예시

- 오름차순으로 정렬된 (-10, -5 ,-1, 1, 3, 4, 22 ,31) 에서 -1과 5를 검색해보자.
- -1 검색
  - 배열의 맨 왼쪽과 오른쪽 그리고 중간값을 비교해본다.
  - -1과 같지 않으므로 중간값인 1과 찾을 값인 -1을 비교하면 찾을 값이 더 작기 때문에 왼쪽값+1, 중간값-1 인 범위에서 다시 검색한다.
  - 위와 같은 방법을 반복하여 -1을 찾게 된다.
- 5 검색
  - 배열의 맨 왼쪽과 오른쪽 그리고 중간값을 비교해본다.
  - 5와 같지 않으므로 중간값인 1과 찾을 값인 5를 비교하면 찾을 값이 더 크기 때문에 중간값+1, 오른쪽값-1 인 범위에서 다시 검색한다.
  - (3, 4, 22)에서 역시 검색하고 존재하지 않음을 알게 된다. 이후 검색 범위를 좁혔을 때 길이가 1이하가 되므로 값이 없음을 리턴한다.



#### 이진 검색 예시 코드

```java
	public static boolean binarySearch(int[] arr, int value, 
			int leftIndex, int rightIndex) {
		

		if (leftIndex >= rightIndex) {
			System.out.println(value + " 은(는) 존재하지 않습니다.");
			return false;
		}

		int midIndex = (rightIndex + leftIndex) / 2;
		if (arr[midIndex] == value 
				|| arr[leftIndex]==value 
				|| arr[rightIndex]==value) {
			System.out.print(value + " 이(가) 존재합니다. ");
			System.out.println("index : " + midIndex);
			return true;
		}

		if (value < arr[midIndex]) {
			return binarySearch(arr, value, leftIndex +1, midIndex - 1);
		} else {
			return binarySearch(arr, value, midIndex+1, rightIndex-1);
		}
	}

	public static void main(String[] args) {
		int[] arr = { -10, -5, -1, 1, 3, 4, 22, 31 };
		binarySearch(arr, -1, 0, arr.length - 1);
		binarySearch(arr, 5, 0, arr.length - 1);

	}
```

```java
결과 : 
-1 이(가) 존재합니다. index : 1
5 은(는) 존재하지 않습니다.

```



#### 이진 검색의 특징

- 정렬이 되어 있어야 한다는 조건이 있지만, 조건 하에서 아주 빠른 효율을 지닌 검색 방법이다.
- 시도 횟수가 늘어날 수록 찾을 범위는 반으로 줄기 때문에 시간복잡도가 O(logn)이다.