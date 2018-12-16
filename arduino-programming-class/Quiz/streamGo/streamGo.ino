#include <Wire.h>

#include <LiquidCrystal_I2C.h>

/*
Plus Quiz) 사운드 감지 센서를 활용하여 스마트폰 게임 '스크림 고(Scream Go)'게임을 만들어 보자.
'스크림 고' 게임은 게이머가 소리를 내야 게임속 케릭터를 움직일 수 있는 게임이다. 
우리도 사운드 센서와 LCD 액정을 활용하여, 사운드가 감지되면 액정에 표시된 케릭터가 좌측에서 우측으로 이동하도록 처리해 보자
*/

int sound_pin = A0;
boolean isRight = true;
int count = 0;


// Set the LCD address to 0x27 for a 16 chars and 2 line display
LiquidCrystal_I2C lcd(0x27, 16, 2); 

void setup() {
  Serial.begin(9600);
  // initialize the LCD
  lcd.begin();

  // Turn on the blacklight and print a message.
  lcd.backlight();
  
  lcd.print("@=(^0^)@");
  
  
}

void loop() {
  int sound_value = analogRead(sound_pin);

  if (count >= 16) {
    isRight = false;
  }

  if (count < 0 ) {
    isRight = true;
  }

  if (sound_value >= 1000 && isRight) {
    lcd.scrollDisplayRight();
    count++;
  }

  if (sound_value >= 1000 && !isRight) {
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

  
  
  

  

  Serial.print("sound value : ");
  Serial.println(sound_value);

  

  
  delay(100);
}
