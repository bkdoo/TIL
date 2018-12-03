int led = 13;



void setup() {
  pinMode(led, OUTPUT);
}

void loop() {
  digitalWrite(led, HIGH);
  delay(10000);
    digitalWrite(led, LOW);
  delay(10000);
}
