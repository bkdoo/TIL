## 안드로이드 - 서비스

- 서비스는 백그라운드 작업을 위한 컴포넌트이며 화면과 상관없이 장시간 동안 처리해야 하는 작업을 구현할 때 사용된다.

- 서비스는 다른 앱이 사용자 화면을 점유하고 있어도 앱의 프로세스가 계속 어떤 작업을 수행해야 할 때 사용한다. 예) mp3플레이어

- 서비스는 Service라는 클래스를 상속받아 작성한다.

- 이후 서비스를 AndroidManifest.xml 파일에 등록해 주어야 한다.

  ```xml
  <service
  android:name=".MyService"
  android:enabled="true"
  android:exported="true" />
  ```

- 이렇게 만든 서비스를 실행하려면 startService() 함수를 이용한다.

  ```java
  Intent intent = new Intent(MainActivity.this, MyService.class);
  startService(intent);
  ```

- 액티비티나 브로드캐스트 리시버와 마찬가지로 인텐트에 데이터를 전달 할 수도 있으며, 서비스는 액티비티나 브로드캐스트 리시버와 다르게 서비스를 종료하는 stopService() 함수를 제공한다. 서비스 내부에서 종료할 때는 stopSelf()함수를 활용한다.

  ```java
  Intent intent = new Intent(MainActivity.this, MyService.class);
  stopService(intent);
  ```

- 서비스 생명주기

  1. startService() 함수가 실행되면 onCreate() > onStartCommand() 함수 순으로 호출되어 Running 상태가 된다.
  2. stopService() 함수로 서비스가 종료될 때는 onDestroy() 함수가 호출되면서 종료된다.

  단, Running 상태인 서비스를 다시 startService() 함수로 실행하면 객체가 다시 생성되지 않고(싱글톤 패턴), onCreate() 함수도 호출되지 않으며, onStartCommend() 함수만 한번 호출된다.

