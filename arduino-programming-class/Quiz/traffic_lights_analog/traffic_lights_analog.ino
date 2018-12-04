/*LED 3개(red, yellow, green)를 red > yellow > green 순서로 점점 밝아지다가 어두어 지는 형태로 순서대로 점등하는 아두이노 코드를 작성하자.*/

int red =11;
int yellow =10;
int green=9;



void setup() {
  pinMode(red, OUTPUT);
  pinMode(yellow, OUTPUT);
  pinMode(green, OUTPUT);

}

void loop() {
  for (int i = 0; i < 255; i++)
  {
    analogWrite(red, i);
    delay(20);
  }
  for (int i = 255; i > 0; i--)
  {
    analogWrite(red, i);
    delay(20);
  }
  for (int i = 0; i < 255; i++)
  {
    analogWrite(yellow, i);
    delay(20);
  }
  for (int i = 255; i > 0; i--)
  {
    analogWrite(yellow, i);
    delay(20);
  }
  for (int i = 0; i < 255; i++)
  {
    analogWrite(green, i);
    delay(20);
  }
  for (int i = 255; i > 0; i--)
  {
    analogWrite(green, i);
    delay(20);
  }
  

}
