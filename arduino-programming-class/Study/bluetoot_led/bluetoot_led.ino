#include <SoftwareSerial.h>

SoftwareSerial bluetooth(2, 3); // 8번 TX, 9번 RX

void setup()
{
  Serial.begin(9600);
  bluetooth.begin(9600);

  pinMode(7, OUTPUT); // LED OUTPUT
  pinMode(8, OUTPUT); // LED OUTPUT
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
    digitalWrite(7,HIGH);
  }

  if(val == 'b')
  {
    digitalWrite(8, HIGH);
  }

  else if(val == 'c')
  {
    digitalWrite(7, LOW);
    digitalWrite(8, LOW);
  }
  
}
