int a_pin = 3;
int b_pin = 7;
int c_pin = A2;
int d_pin = A4;
int e_pin = A5;
int f_pin = 4;
int g_pin = A1;
int dp_pin = A3;

int dig1 = 2;
int dig2 = 5;
int dig3 = 6;
int dig4 = A0;

#define C  262
#define D  294
#define E  330
#define F  349
#define G  392
#define A  440
#define B  494

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

int digitPos[4] = {dig1, dig2, dig3, dig4};

int val = 0;
int val4 = 0;
int val3 = 0;
int val2 = 0;
int val1 = 0;

int piezo = 13;

void setup() {
  // put your setup code here, to run once:
  pinMode(dig1, OUTPUT);
  pinMode(dig2, OUTPUT);
  pinMode(dig3, OUTPUT);
  pinMode(dig4, OUTPUT);
  
  pinMode(a_pin, OUTPUT);
  pinMode(b_pin, OUTPUT);
  pinMode(c_pin, OUTPUT);
  pinMode(d_pin, OUTPUT);
  pinMode(e_pin, OUTPUT);
  pinMode(f_pin, OUTPUT);
  pinMode(g_pin, OUTPUT);
  
  pinMode(dp_pin, OUTPUT);
}

void loop() {
  

  if(val >=15) {
    val = 15;
  } else {
    val = millis() / 1000;
  }

  
  val2 = (val / 10) % 10;
  val1 = val % 10;

  

  if (val >= 10)
  {
    clearAll();
    selectDigitPos(3);
    printNumber(val2);
    delayMicroseconds(1000);
  }

  if (val >= 0)
  {
    clearAll();
    selectDigitPos(4);
    printNumber(val1);
    delayMicroseconds(1000);
  }

  if (val == 15){
    schoolbell_front(piezo);
    
    


  }
  
}

void selectDigitPos(int pos) {
  digitalWrite(dig1, HIGH);
  digitalWrite(dig2, HIGH);
  digitalWrite(dig3, HIGH);
  digitalWrite(dig4, HIGH);
  

  digitalWrite(digitPos[pos-1], LOW);
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

void printDP() {
  digitalWrite(dp_pin, HIGH);
}
void setSound(int piezo, int note, int meter) {
  tone(piezo, note, meter);
  delay(meter);
  noTone(piezo);
}

void schoolbell_front(int piezo){
  setSound(piezo, G, 300);
  setSound(piezo, G, 300);
  setSound(piezo, A, 300);
  setSound(piezo, A, 300);
  setSound(piezo, G, 300);
  setSound(piezo, G, 300);
  setSound(piezo, E, 600);
  setSound(piezo, G, 300);
  setSound(piezo, G, 300);
  setSound(piezo, E, 300);
  setSound(piezo, E, 300);
  setSound(piezo, D, 900);
  
}
