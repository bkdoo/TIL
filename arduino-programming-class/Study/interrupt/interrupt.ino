/*
가변저항, 버튼, 서보모터를 활용하여 가변저항으로 0~180도를 지정하고 
버튼을 눌러야 서보모터가 이동하도록 회로와 아두이노 코드를 작성하시오
*/

int btn1_pin = 2;
int btn2_pin = 3;

volatile int count1 = 0;
volatile int count2 = 1;

void setup() {
  Serial.begin(9600);
  pinMode(btn1_pin, OUTPUT);
  pinMode(btn2_pin, OUTPUT);

  attachInterrupt(0, btn1ISR, FALLING);
  attachInterrupt(1, btn2ISR, CHANGE);
}

void loop() {
  delay(1000);
}

void btn1ISR() {
  count1 += 1;
  Serial.print("count1 : ");
  Serial.println(count1);
}

void btn2ISR() {
  count2 += 1;
  Serial.print("count2 : ");
  Serial.println(count2);
}
