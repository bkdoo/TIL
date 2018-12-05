int pins[] = {2,4,7};
int notes[] = {262, 294, 330};
int piezo = 8;

void setup() {
  for (int i = 0; i < 3; i++)  {
    pinMode(pins[i], INPUT);
  }
}

void loop() {
  for (int i = 0; i < 3; i++) {
    if (digitalRead(pins[i])==HIGH){
      tone(piezo,notes[i], 100);
      delay(100);
      noTone(piezo);
    }
  }
}
