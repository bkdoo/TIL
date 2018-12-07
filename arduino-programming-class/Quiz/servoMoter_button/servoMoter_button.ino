/*
가변저항, 버튼, 서보모터를 활용하여 가변저항으로 0~180도를 지정하고 
버튼을 눌러야 서보모터가 이동하도록 회로와 아두이노 코드를 작성하시오
*/

#include <Servo.h>

Servo myServo;
int btn = 8;

void setup(){
  myServo.attach(9);
  pinMode(btn , INPUT);
}

void loop(){
  int val = analogRead(A0);
  int rad = map(val, 0, 1023, 0, 180);
  if(digitalRead(btn)==HIGH){
    myServo.write(rad);  
  }
  
  delay(15);
}
