/*
LED 3개(red, yellow, green)가 각 버튼을 눌렸을 때 다음과 같은 시나리오로 점등되는 아두이노 코드를 작성하자.
시나리오
왼쪽 LED 버튼 : 모든 LED가 꺼진 후 6초동안 노랑색 LED가 깜빡이고 빨강색 LED가 계속 점등된다.

가운데 LED 버튼 : 모든 LED가 꺼진 후 6초 동안 노랑색 LED가 깜빡이고 초록색 LED가 계속 점등된다.

오른쪽 LED 버튼 : 모든 LED가 꺼진다.
*/

int red =13;
int yellow =12;
int green=8;

int redBtn = 7;
int yellowBtn =4;
int greenBtn = 2;

int r, y, g;



void setup() {
  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT);

  pinMode(redBtn, INPUT);
  pinMode(yellowBtn, INPUT);
  pinMode(greenBtn, INPUT);

}

void loop() {
  r = digitalRead(redBtn);
  y = digitalRead(yellowBtn);
  g = digitalRead(greenBtn);

  if (r == HIGH)  {
    lightOffAll();
    delay(1000);

    flash(yellow);

    digitalWrite(red,1);
  } 

  if (y == HIGH)  {
    lightOffAll();
    delay(1000);

    flash(yellow);

    digitalWrite(green,1); 
  } 

  if (g == HIGH)  {
    lightOffAll();  
  } 

  
  
}

//6초간 led점등 메소드
void flash(int color){
  for (int i = 1; i <= 12; i++)
  {
    if (i %2 == 1) {
      digitalWrite(color,1);
        delay(500);
    } else {
      digitalWrite(color,0);
        delay(500);
    }
  }

}

void lightOffAll(){
    digitalWrite(red,0);
    digitalWrite(yellow,0);
    digitalWrite(green,0); 
}



 
