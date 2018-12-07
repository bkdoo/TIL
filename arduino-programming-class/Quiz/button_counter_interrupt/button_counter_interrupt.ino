/*
가변저항, 버튼, 서보모터를 활용하여 가변저항으로 0~180도를 지정하고 
버튼을 눌러야 서보모터가 이동하도록 회로와 아두이노 코드를 작성하시오
*/
int a_pin = 4;
int b_pin = 6;
int c_pin = A2;
int d_pin = A4;
int e_pin = A5;
int f_pin = 5;
int g_pin = A1;
int dp_pin = A3;


int dig4 = A0;

int plusBtn = 8;
int minusBtn = 13;

byte digits[10][7] = {
  {1,1,1,1,1,1,0},
  {0,1,1,0,0,0,0},
  {1,1,0,1,1,0,1},
  {1,1,1,1,0,0,1},
  {0,1,1,0,0,1,1},
  {1,0,1,1,0,1,1},
  {1,0,1,1,1,1,1},
  {1,1,1,0,0,0,0},
  {1,1,1,1,1,1,1},
  {1,1,1,1,0,1,1}
};






int btn1_pin = 2;
int btn2_pin = 3;

volatile int val = 0;


void setup() {
  Serial.begin(9600);
  pinMode(btn1_pin, OUTPUT);
  pinMode(btn2_pin, OUTPUT);

  attachInterrupt(0, btn1ISR, FALLING);
  attachInterrupt(1, btn2ISR, FALLING);

  
  pinMode(dig4, OUTPUT);
  
  pinMode(a_pin, OUTPUT);
  pinMode(b_pin, OUTPUT);
  pinMode(c_pin, OUTPUT);
  pinMode(d_pin, OUTPUT);
  pinMode(e_pin, OUTPUT);
  pinMode(f_pin, OUTPUT);
  pinMode(g_pin, OUTPUT);
  
  pinMode(dp_pin, OUTPUT);

  pinMode(btn1_pin, INPUT);
  pinMode(btn2_pin, INPUT);
}

void loop() {
  clearAll();
  digitalWrite(A0, LOW);
  printNumber(val);
  delay(1000);
}

void btn1ISR() {
  if(val >= 9){
    val = 0;
  } else {
    val += 1;  
  }
  
}

void btn2ISR() {
  if(val<=0) {
    val = 9;
  } else {
    val -= 1;  
  }
  
 
}

void printNumber(int number) {
  digitalWrite(a_pin, digits[number][0]);
  digitalWrite(b_pin, digits[number][1]);
  digitalWrite(c_pin, digits[number][2]);
  digitalWrite(d_pin, digits[number][3]);
  digitalWrite(e_pin, digits[number][4]);
  digitalWrite(f_pin, digits[number][5]);
  digitalWrite(g_pin, digits[number][6]);
}

void clearAll() {
  digitalWrite(a_pin, LOW);
  digitalWrite(b_pin, LOW);
  digitalWrite(c_pin, LOW);
  digitalWrite(d_pin, LOW);
  digitalWrite(e_pin, LOW);
  digitalWrite(f_pin, LOW);
  digitalWrite(g_pin, LOW);
  
  digitalWrite(dp_pin, LOW);
}
