## 안드로이드 - Broadcast Receiver

- 브로드캐스팅(Broadcasting)이란 메세지를 여러 객체에게 전달하는 것을 의미.

- 예를 들어 SMS 메세지를 수신했을 때, 이 문자를 다른 앱에 알려줄 필요가 있다면 브로드캐스팅으로 전달한다.

- 이는 단말 전체에도 적용될 수 있는데 이를 '글로벌 이벤트(Global Event)'라고 부른다.

- Activity에선 1대1로 인텐트를 전달하지만, Broadcast Reciver는 1대다로 전달하며, 수신하는 리시버가 없더라도 에러가 발생하지 않는다.

- 액티비티가 화면을 출력하는 역할을 수행한다면, 브로드 캐스트는 대부분 간단한 업무로 사용되거나 아니먼 브로드캐스트 리시버에서 다른 액티비티나 서비스를 실행하는 역할로 활용된다.

- Broadcast Recevier 또한 애플리케이션 구성 요소이므로 Manifest에 등록해야하지만 예외적으로 자바 코드에서 registerReceiver() 메소드를 사용해 등록할 수 있다.

- 서비스처럼 Broadcast Recevier 역시 화면이 없다. 

- BroadcastRecevier를 상속받고, onReceive()라는 메소드를 재정하여 사용한다. 이 메소드는 브로드캐스트 메세지가 도착하면 자동으로 호출된다.

- 메세지는 인텐트 안에 넣어 전달되므로 인텐트 필터를 이용해 등록한다.

  ```java
  registerReceiver(screenOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
  ```

  ```xml
  <receiver
      android:name=".MyReceiver2"
      android:enabled="true"
      android:exported="true">
      <intent-filter>
       <action android:name="android.intent.action.BOOT_COMPLETED"/>
      </intent-filter>
  </receiver>
  ```

- 시스템 브로드 캐스트 인텐트

  브로드 캐스트 리시버는 앱 내부에서 사용하기 위해 자주 만들지만 시스템에서 발생하는 인텐트를 활용하여 각종 시스템 상황을 감지하기 위해서도 많이 사용한다.

  - 부팅 완료

    AndroidManifest.xml 파일에 등록할 때 시스템에서 부팅 완료시점에 띄우는 인텐트 action 문자열(android.intent.action.BOOT_COMPLETED)을 intent-filter에 입력한다.

    ```xml
    <receiver
        android:name=".MyReceiver2"
        android:enabled="true"
        android:exported="true">
        <intent-filter>
        	<action android:name="android.intent.action.BOOT_COMPLETED"></action>
        </intent-filter>
    </receiver>
    ```

    부팅 완료 시점에 브로드 캐스트 리시버가 동작하게 하려면 퍼미션이 등록되어 있어야 한다.

    ```xml
    <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
    
    ```

  - 디스플레이 On/Off

    화면이 On / Off 되는 상황에 시스템에서는 브로드 캐스트 인텐트를 발생해 주며 앱에서 브로드캐스트 리시버를 이용하여 감지할 수 있다.

    단, 화면 On / Off를 위한 브로드 캐스트 리시버는 다른 브로드캐스트 리시버와는 다르게 AndroidManifest.xml 파일에 태그로 등록하지 않고 액티비티 혹은 서비스의 코드에서 동적으로 등록해야 한다.

    ```java
    registerReceiver(screenOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
    registerReceiver(screenOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    ```

    코드에서 동적으로 등록한 브로드캐스트 리시버는 동적으로 등록을 해제할 수 있다. 해제는 unregisterReceiver() 함수를 이용한다.

    ```java
    unregisterReceiver(screenOn);
    unregisterReceiver(screenOff);
    ```
