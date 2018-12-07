#include <Servo.h>

Servo myServo;

void setup(){
  myServo.attach(9);
}

void loop(){
  int val = analogRead(A0);
  int rad = map(val, 0, 1023, 0, 180);
  myServo.write(rad);
  delay(15);
}
