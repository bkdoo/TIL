/*
2개와 피에조 스피터 1개를 활용하여 
버튼1을 눌렀을 때 동요 '학교종이 땡땡땡'의 앞소절을 
버튼2를 눌렀을 때 뒷소절을 소리내 보자.
*/

int front = 2;
int back = 4;


int piezo = 8;

void setup() {
  pinMode(front, INPUT);
  pinMode(back, INPUT);
}

void loop() {
  
    if (digitalRead(front)==HIGH){
      schoolbell_front(piezo);
    }

    if (digitalRead(back)==HIGH){
      schoolbell_back(piezo);
    }
  
}

void schoolbell_front(int piezo){
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 440, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 440, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 1000);
  delay(1000);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 294, 1500);
  delay(1500);
  noTone(piezo);
  
}

void schoolbell_back(int piezo){
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 440, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 440, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 1000);
  delay(1000);
  noTone(piezo);
  tone(piezo, 392, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 294, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 330, 500);
  delay(500);
  noTone(piezo);
  tone(piezo, 262, 1500);
  delay(1500);
  noTone(piezo);
}
