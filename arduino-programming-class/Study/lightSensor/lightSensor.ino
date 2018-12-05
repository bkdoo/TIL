
int led = 11;
int piezo = 8;


void setup() {
    
}

void loop() {
  int light = analogRead(A0);
  int ledLight = map(light, 0, 1023, 255, 0);
  analogWrite(led, ledLight);

  int hertz = map(light, 0, 500, 31, 4978);
  tone(piezo, hertz, 50);
  delay(50);
  noTone(8);
  delay(50);
}
