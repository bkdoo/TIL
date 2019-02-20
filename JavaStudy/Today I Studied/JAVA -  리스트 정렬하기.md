## JAVA -  리스트 정렬하기

#### Comparable과 Comparator 인터페이스의 차이

- Comparable 인터페이스는 자연스러운 순서로 정렬할 때 사용
- Comparator는 원하는 대로 정렬 순서를 지정하고 싶은 곳에 사용



#### Comparable 인터페이스

- Comparable은 기본 정렬 기준(오름차순, 사전순) 형태로 구현되도록 만들어져있다.
- String, Integer, Date, File 등 Java에서 제공되는 정렬이 가능한 클래스들은 모두 Comparable 인터페이스를 구현하고 있다.
- Arrays.sort(), Collections.sort() 는 Comparable 구현에 의해 정렬된다.
- Comparable이 구현되지 않은 클래스(새로 만든 클래스 등)는 sort()시  Unresolved compilation problem 에러가 뜨게 된다.
- 따라서 Comparable 인터페이스를 구현하고 compareTo 메소드를 오버라이딩 해야한다.
- compareTo() 메소드 작성법
  - 현재 객체 < 파라미터로 넘어온 객체 : 음수 리턴
  - 현재 객체 == 파라미터로 넘어온 객체 : o 리턴
  - 현재 객체 > 파라미터로 넘어온 객체 : 양수 리턴
  - 양수인 경우에는 두 객체의 위치가 바뀐다(오름차순, 사전순)

```java
public class Sport implements Comparable<Sport> {
	private String name;
	private int numOfPlayers;

	...

	@Override
	public int compareTo(Sport o) {
		return name.compareTo(o.getName());
	}

}
```

```java
		Sport[] sports = new Sport[4];
		sports[0] = new Sport("Soccer", 22);
		sports[1] = new Sport("Baseball", 18);
		sports[2] = new Sport("BasketBall", 10);
		sports[3] = new Sport("Squash", 2);
		
		Arrays.sort(sports);
		
		for (Sport sport : sports) {
			System.out.print(sport.getName() + " ");
		}
		System.out.println();
```

```
결과 : Apple Banana Orange Pineapple 
```



#### Comparator 

- Comparable에 구현된 정렬 방법이 아닌 다른 방법으로 정렬을 하고자 할 때 사용한다.
- 주로 익명 객체로 사용한다.
- 위의 코드에서 이름순이 아닌 플레이어 수를 기준으로 정렬해보자.

```java
		Arrays.sort(sports , new Comparator<Sport>() {

			@Override
			public int compare(Sport sport1, Sport sport2) {
				int num1 = sport1.getPlayers();
				int num2 = sport2.getPlayers();
				//오름차순
				//return num1-num2;
				//내림차순
				return num2-num1;
			}
			
		});
		
		for (Sport sport : sports) {
			System.out.println(sport.getName() + ":" + sport.getPlayers());
		}
```

```
오름차순 결과 : Squash:2
BasketBall:10
Baseball:18
Soccer:22

내림차순 결과 : Soccer:22
Baseball:18
BasketBall:10
Squash:2
```

- compare 메소드 작성법
  - 두 번째 매개변수 앞에 첫 번째 매개변수가 오면 음수 값 리턴
  - 같으면 0 리턴
  - 두 번째 매개변수가 첫 번째 매개변수보다 앞에 오면 양수 값 리턴

