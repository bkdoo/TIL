/*LED 3개(red, yellow, green)가 각 버튼을 누르면 점등되는 아두이노 코드를 작성하자.*/

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

  if (r == HIGH)
  {
    digitalWrite(red, HIGH);  
  } else {
    digitalWrite(red, LOW); 
  }

  if (y == HIGH)
  {
    digitalWrite(yellow, HIGH); 
  } else {
    digitalWrite(yellow, LOW);  
  }

  if (g == HIGH)
  {
    digitalWrite(green, HIGH);  
  } else {
    digitalWrite(green, LOW); 
  }

  
  
}
