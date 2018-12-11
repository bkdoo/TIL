#include <Servo.h>

#include <SoftwareSerial.h>

SoftwareSerial bluetooth(2, 3); // 8번 TX, 9번 RX
Servo myServo;

void setup()
{
  Serial.begin(9600);
  bluetooth.begin(9600);

  pinMode(7, OUTPUT); // LED OUTPUT
  pinMode(8, OUTPUT); // LED OUTPUT
  myServo.attach(9);
}

void loop()
{
  char val = bluetooth.read();

  if (bluetooth.available())
  {
    Serial.write(bluetooth.read());
  }

  if(val == 'a')
  {
    myServo.write(120);
  }

  if(val == 'd')
  {
    myServo.write(0);
  }

  if(val == 'w')
  {
    myServo.write(60);
  }

  if(val == 's')
  {
    for(int i= 0; i<=120; i++){
      myServo.write(i);
    }
    
    for(int i= 120; i>=0; i--){
      myServo.write(i);
    }
  }
  
}
