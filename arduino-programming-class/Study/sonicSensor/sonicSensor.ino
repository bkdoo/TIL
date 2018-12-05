
int trig = 13;
int echo = 11;
int piezo = 8;


void setup() {
    Serial.begin(9600);
    pinMode(trig, OUTPUT);
    pinMode(echo, INPUT);
}

void loop() {
  //10 us만큼 trigger on
  digitalWrite(trig, HIGH);
  delayMicroseconds(10);
  digitalWrite(trig, LOW);

  //해당 디지털 입력 핀의 전압이 LOW 또는 HIGH가 될 때까지 걸린 시간을 재는 함수.
  long duration = pulseIn(echo, HIGH);

  //58.2 : 음속을 2로 나누고 단위변경(cm)
  long distance = duration / 58.2;
  tone(piezo, 262, 20);
  delay(20);
  noTone(piezo);
  delay(2 * distance);
  
  Serial.print(distance);
  Serial.println("cm");
  delay(100);
  
}
