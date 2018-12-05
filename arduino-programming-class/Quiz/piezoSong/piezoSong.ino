#define C  262
#define D  294
#define E  330
#define F  349
#define G  392
#define A  440
#define B  494
#define CC  523
#define DD  587
#define EE  659
#define FF  698
#define GG  784
#define AA  880
#define BB  988
#define CCC 1047
#define DDD 1175







int front = 2;
int back = 4;
int bts = 7;


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

    if (digitalRead(bts)==HIGH){
      piddam(piezo);
    }
  
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

void schoolbell_back(int piezo){
  setSound(piezo, G, 300);
  setSound(piezo, G, 300);
  setSound(piezo, A, 300);
  setSound(piezo, A, 300);
  setSound(piezo, G, 300);
  setSound(piezo, G, 300);
  setSound(piezo, E, 600);
  setSound(piezo, G, 300);
  setSound(piezo, E, 300);
  setSound(piezo, D, 300);
  setSound(piezo, E, 300);
  setSound(piezo, C, 900);
}

void piddam(int piezo){
  setSound(piezo, D, 200);//내
  setSound(piezo, D, 400);//피
  setSound(piezo, A, 400);//땀
  setSound(piezo, G, 400);//눈
  setSound(piezo, A, 200);//물
  setSound(piezo, A, 600);//물

  delay(800);

  setSound(piezo, D, 200);//내
  setSound(piezo, D, 400);//마
  setSound(piezo, D, 200);//지
  setSound(piezo, A, 400);//막
  setSound(piezo, G, 400);//춤
  setSound(piezo, G, 200);//을
  setSound(piezo, A, 200);//을
  
  setSound(piezo, G, 800);//다
  delay(400);
  setSound(piezo, F, 400);//가
  setSound(piezo, F, 400);//져

  setSound(piezo, DD, 200);//가~
  setSound(piezo, CC, 300);
  setSound(piezo, A, 200);
  setSound(piezo, CC, 200);
  delay(200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 400);
  setSound(piezo, F, 400);
  setSound(piezo, D, 400);
  delay(200);

  

  setSound(piezo, DD, 200);//가~
  setSound(piezo, CC, 30);
  setSound(piezo, A, 200);
  setSound(piezo, CC, 200);
  delay(200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 600);

  setSound(piezo, D, 200);//내
  setSound(piezo, D, 400);//피
  setSound(piezo, A, 400);//땀
  setSound(piezo, G, 400);//눈
  setSound(piezo, A, 200);//물
  setSound(piezo, A, 600);//물

  delay(800);

  setSound(piezo, D, 200);//내
  setSound(piezo, D, 400);//마
  setSound(piezo, D, 200);//지
  setSound(piezo, A, 400);//막
  setSound(piezo, G, 400);//춤
  setSound(piezo, G, 200);//을
  setSound(piezo, A, 200);//을
  
  setSound(piezo, G, 800);//다
  delay(400);
  setSound(piezo, F, 400);//가
  setSound(piezo, F, 400);//져

  setSound(piezo, DD, 200);//가~
  setSound(piezo, CC, 300);
  setSound(piezo, A, 200);
  setSound(piezo, CC, 200);
  delay(200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 400);
  setSound(piezo, F, 400);
  setSound(piezo, D, 400);
  delay(200);

  

  setSound(piezo, DD, 200);//가~
  setSound(piezo, CC, 300);
  setSound(piezo, A, 200);
  setSound(piezo, CC, 200);
  delay(200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 200);
  setSound(piezo, A, 200);
  setSound(piezo, G, 600);

}
