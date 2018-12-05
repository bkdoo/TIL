int melody[] = { 523, 494, 440, 392, 349, 330, 294, 262};
//도레미파솔라시도

void setup() {
  for (int i = 0; i < 8; i++)  {
    tone(8, melody[i], 250);
    delay(400);
    noTone(8);
  }
}

void loop() {
}
