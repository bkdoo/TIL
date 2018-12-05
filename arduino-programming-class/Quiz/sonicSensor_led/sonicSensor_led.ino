/*
초음파 거리센서로 거리를 출력하는 회로와 코드를 작성해보자. 
측정된 거리 5cm 이상 10cm 미만이면 초록색 LED를 점등하고,
이외는 빨간색 LED로 점등된다.
단, LED는 버튼이 눌린 상태에서만 점등된다.
*/


int trig = 13;
int echo = 11;
int green = 2;
int red = 4;
int btn = 7;


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
  
  if(digitalRead(btn)==HIGH) {
    if(distance >=5 && distance <10){
      digitalWrite(green, HIGH);
      digitalWrite(red, LOW);
      
    } else {
      digitalWrite(green, LOW);
      digitalWrite(red, HIGH);
    } 
    
  }else {
      digitalWrite(red, LOW);
      digitalWrite(green, LOW);
    }
  
  
  delay(100);
  
}
