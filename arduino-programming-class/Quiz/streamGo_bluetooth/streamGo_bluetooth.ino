#include <SoftwareSerial.h>

#include <Wire.h>

#include <LiquidCrystal_I2C.h>

/*
Plus Quiz) 사운드 감지 센서를 활용하여 스마트폰 게임 '스크림 고(Scream Go)'게임을 만들어 보자.
'스크림 고' 게임은 게이머가 소리를 내야 게임속 케릭터를 움직일 수 있는 게임이다. 
우리도 사운드 센서와 LCD 액정을 활용하여, 사운드가 감지되면 액정에 표시된 케릭터가 좌측에서 우측으로 이동하도록 처리해 보자
*/



int count = 0;


// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16, 2); 

SoftwareSerial bluetooth(2, 3);


void setup() {
  Serial.begin(9600);
  bluetooth.begin(9600);
  // initialize the LCD
  lcd.begin();

  // Turn on the blacklight and print a message.
  lcd.backlight();
  
  lcd.print("@=(^0^)@");
  
  
}

void loop() {
  
  
  char val = bluetooth.read();

  

  if (val=='d') {
    lcd.scrollDisplayRight();
    count++;
  }

  if (val=='a') {
    lcd.scrollDisplayLeft();
    count--;
  }

  if(count %2 == 1){
    lcd.clear();
    lcd.setCursor(count, 0);
    lcd.print("@(^0^)=@");
    
  } else {
    lcd.clear();
    lcd.setCursor(count, 0);
    lcd.print("@=(^0^)@");
    
  }

  if (bluetooth.available()) {       
    Serial.write(bluetooth.read());  //블루투스측 내용을 시리얼모니터에 출력
  }
  if (Serial.available()) {         
    bluetooth.write(Serial.read());  //시리얼 모니터 내용을 블루추스 측에 WRITE
  }
  
  

  

 

  

  
  delay(100);
}
