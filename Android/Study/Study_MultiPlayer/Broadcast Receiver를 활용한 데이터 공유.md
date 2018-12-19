## Broadcast Receiver를 활용한 데이터 공유

#### 데이터의 공유

- 서비스에서 데이터가 발생한 순간 다른 액티비티에 전댈하야 하는 상황이 있다
- 혹은 반대로 액티비티에서 데이터가 발생한 순간 서비스로 전달해 주어야 하는 경우도 있다.
- 인텐트에 데이터를 추가하여 액티비티와 서비스 간 데이터를 전달할 수는 있지만, 시점이 최초로 구동하는 때에 한정된다.
- 따라서 액티비티나 서비스 내부에 Broadcast Receiver를 정의하고 데이터를 브로드캐스트 인텐트에 Extra하여 전달하는 방법을 사용한다.
- 인텐트를 전송할 때는 sendBroadcast() 메소드를 사용하고, 수신할 때는 onReceive() 메소드를 재정의 하여 사용한다.

```java
Intent intent = new Intent("com.example.student.study_multiplayer");
intent.putExtra("mode", "send1");
sendBroadcast(intent); //브로드캐스트 전송
```

```java
 public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
}
```

