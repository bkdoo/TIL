## 안드로이드 - 서비스

- 서비스는 백그라운드에서 실행되는 프로세스를 의미.

- 액티비티와의 다른점은 화면이 없다는 것, 화면없이 액티비티처럼 하나의 애플리케이션 구셩 요소로 동작한다.

- 서비스는 백그라운드 작업을 위한 컴포넌트이며 화면과 상관없이 장시간 동안 처리해야 하는 작업을 구현할 때 사용된다.

- 서비스는 다른 앱이 사용자 화면을 점유하고 있어도 앱의 프로세스가 계속 어떤 작업을 수행해야 할 때 사용한다. 예) mp3플레이어

- 서비스도 애플리케이션의 구성 요소이므로 시스템에서 관리하며 반드시 AndroidManifest.xml 파일에 등록해 주어야 한다.

  ```xml
  <service
  android:name=".MyService"
  android:enabled="true"
  android:exported="true" />
  ```

- 서비스는 Service라는 클래스를 상속받아 작성하며, 메인 액티비티에서 startService() 메소드를 호출하여 사용한다.

  ```java
  Intent intent = new Intent(MainActivity.this, MyService.class);
  startService(intent);
  ```

- 서비스가 하는 중요한 역할은 단말이 항상 실행되어 있는 상태로 다른 단말과 데이터를 주고받거나 단말의 상태를 모니터하는 것.

- 따라서 실행된 상태가 계속 유지되어야하며 ,이를 위해 서비스가 비정상적으로 종료되어도 시스템이 자등으로 재실행한다.

- 서비스가 계속 실행중인 상태라면 startService() 메소드를 여러번 호출하더라도 서비스의 상태에는 변화가 없다.

- 따라서, startService()는 서비스를 시작하는 목적보다는 인텐트를 전달하는 목적으로 더 많이 사용된다.

  ```java
  Intent intent = new Intent(getApplicationContext(), MyService.class);
  startService(intent);
  ```

- 이 때 서비스 객체가 메모리에 만들어져 있으면 onCreate() 메소드가 호출되지 않으므로(싱글톤 패턴) onStartCommand() 메소드를 사용해 전달 받은 인텐트를 처리하게 된다.

- 서비스는 액티비티나 브로드캐스트 리시버와 다르게 서비스를 종료하는 stopService() 함수를 제공한다. 서비스 내부에서 종료할 때는 stopSelf()함수를 활용한다.

  ```java
  Intent intent = new Intent(MainActivity.this, MyService.class);
  stopService(intent);
  ```

- 서비스 클래스는 다른 구성요소와의 유기적으로 연결되도록 바인딩될 수 있는데 이때는 onBind()메소드를 재정의하면 된다.

- 서비스에서 액티비티로 데이터를 전달 할 때는 서비스에서 startActivity() 메소드를 사용한다.

- 서비스 생명주기

  1. startService() 함수가 실행되면 onCreate() > onStartCommand() 함수 순으로 호출되어 Running 상태가 된다.
  2. stopService() 함수로 서비스가 종료될 때는 onDestroy() 함수가 호출되면서 종료된다.
