
#define C  262
#define D  294
#define E  330
#define F  349
#define G 392
#define A  440
#define B  494
#define CC 523

int piezo = 8;
int notes[] = {E,F,CC,A,G,F,F,CC,A,G,F,F,F,F,F,F,CC,F,F,C,C,F};



void setup() {
 
}

void loop() {
  for(int i = 0; i<22; i++){
    
    tone(piezo, notes[i], analogRead(A0));
    delay(analogRead(A0));
  } 
  delay(100);

  // for(int i = 12; i<25; i++){
  //  tone(piezo, notes[i], analogRead(A0));
  //  delay(analogRead(A0));
  // }
  
}
