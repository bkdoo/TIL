#include <Servo.h>

Servo myservo;

void setup()
{
  myservo.attach(9);
}

void loop()
{
  for (int i = 0; i < 120; ++i)
  {
    myservo.write(i);
    if(i <15){
      delay(i);  
    } else {
      delay(15);
    }
    
  }
  for (int i = 120; i > 0; --i)
  {
    myservo.write(i);
    if(i <15){
      delay(i);  
    } else {
      delay(15);
    }
  }
  
  delay(15);
}
