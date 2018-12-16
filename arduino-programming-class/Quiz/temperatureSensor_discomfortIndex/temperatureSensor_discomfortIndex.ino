/*
온도 습도 센서를 활용하여 온도와 습도를 구할 수 있다.
온도와 습도를 활용하여 불쾌지수를 계산할 수 있으며,
공식은 아래와 같이 적용된다.
LCD에 현재 온도, 습도, 불쾌지수를 표시하시오.
*/



#include <LiquidCrystal_I2C.h>

#include <Wire.h>

#include <dht11.h>

int DHT11PIN = A0;            //Signal 이 연결된 아두이노의 핀번호
 
dht11 DHT11;

// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16, 2); 
  
void setup()
{
  Serial.begin(9600);  //시리얼 통신속도 설정
  // initialize the LCD
  lcd.begin();

  // Turn on the blacklight and print a message.
  lcd.backlight();
  
}
  
void loop()
{
  int chk = DHT11.read(DHT11PIN);
  float T = (float)DHT11.temperature;
  float RH = (float)DHT11.humidity;
  float DI = (9*T/5) - 0.55*(1-RH*0.01)*((9*T/5) - 25) + 32;
  lcd.clear();
  
  lcd.print(T);
  lcd.print(" C ");
  
  lcd.print(RH);
  lcd.print(" %");

  lcd.setCursor(0,-1);
  lcd.print(DI);
  lcd.print(" DI");
   
  delay(2000);
}
