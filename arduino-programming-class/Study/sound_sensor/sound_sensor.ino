int sound_pin = A0;
int led_pin = 11;

void setup() {
  Serial.begin(9600);
  pinMode(led_pin, OUTPUT);
}

void loop() {
  int sound_value = analogRead(sound_pin);
  int light_value = map(sound_value, 0, 1023, 0, 255);

  Serial.print("sound value : ");
  Serial.println(sound_value);

  Serial.print("light value : ");
  Serial.println(light_value);

  analogWrite(led_pin, light_value);
  delay(100);
}
