/*
* 키패드에 비밀번호를 입력하고 *키를 눌렀을때
* 비밀번호가 1234면 초록색 led가 깜빡거리고
* 그 이외에는 빨간색 led가 깜빡거리게 하는 아두이노 코드를 작성하자.
* 추가 : C키를 눌렀을 때는 입력된 비밀번호 초기화
* 추가 : #키를 길게 눌렀을 때는 비밀번호 수정모드
*/

#include <Key.h>
#include <Keypad.h>
const byte ROWS = 4;
const byte COLS = 4;

char keys[ROWS][COLS] = {
  {'1', '2', '3', 'A'},
  {'4', '5', '6', 'B'},
  {'7', '8', '9', 'C'},
  {'*', '0', '#', 'D'}
};

byte rowPins[ROWS] = { 9, 8, 7, 6 };
byte colPins[COLS] = { 5, 4, 3, 2 };
String password = "1234";
String password_insert = "";

Keypad keypad = Keypad(makeKeymap(keys), rowPins,colPins, ROWS, COLS);
int rled_pin = 13;
int gled_pin = 12;
boolean blink = false;
boolean onClear = false;

void setup() {
  Serial.begin(9600);
  pinMode(rled_pin, OUTPUT);
  pinMode(gled_pin, OUTPUT);
  keypad.addEventListener(listener);
  
}

void loop() {
  char key = keypad.getKey();
  
}

void listener(KeypadEvent key) {
  
  
  switch(keypad.getState()) {
    case PRESSED:
            
      if(key == '*') {
        if(password_insert==password){
          Serial.println("CLEAR");
          onClear = true;
          digitalWrite(rled_pin, false);
          for(int i = 0; i<10; i++){
          digitalWrite(gled_pin, true);
          delay(100); 
          digitalWrite(gled_pin, false);
          delay(100); 
          }
          
        } else {
          Serial.println("FAIL");
          onClear = false;
          digitalWrite(gled_pin, false);
          for(int i = 0; i<10; i++){
          digitalWrite(rled_pin, true);
          delay(100); 
          digitalWrite(rled_pin, false);
          delay(100); 
          } 
        Serial.println("onclear : " + onClear);       
        }
        password_insert = "";
      } else if(key=='C') {
        password_insert = "";
      } else if(key=='#') {
        
      } else {
        password_insert += key;
      }
      Serial.println("password : " + password_insert); 
      break;

   case HOLD:
    if(onClear) {
      Serial.println("change password mode");
      if(key == '#') {
        changePassword(key);
        Serial.println("new password : " + password);
      }
    }
    
  }
  
}

void changePassword(KeypadEvent key){
  String newPassword = "";

  switch(keypad.getState()) {
    case PRESSED :
    if (key == '*' || key == 'A' || key == 'B' || key == 'C' || key == 'D'){
      digitalWrite(gled_pin, false);
      for(int i = 0; i<10; i++){
      digitalWrite(rled_pin, true);
      delay(100); 
      digitalWrite(rled_pin, false);
      delay(100);
      }
      newPassword = "";
  } else if (key == '#') {
    password = newPassword;
  } else {
    newPassword += key;
  }
  break;
  
  }
}
