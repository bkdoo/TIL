/*
사운스 감지 센서와 LED 3개를 활용하여 감지된 소리의 크기가 최대치 인 횟수에 따라 녹색, 노랑색, 빨강색 LED 를 차례대로 점등하자.
*/

int sound_pin = A0;
int green_pin = 11;
int red_pin = 10;
int yellow_pin = 9;
int count = 0;

void setup() {
  Serial.begin(9600);
  pinMode(green_pin, OUTPUT);
  pinMode(yellow_pin, OUTPUT);
  pinMode(red_pin, OUTPUT);
}

void loop() {
  int sound_value = analogRead(sound_pin);
  
  if (sound_value >= 1023) {
    count += 1;
  }

  if (count >=10) {
    analogWrite(red_pin, 255);
  } else if (count >= 5) {
    analogWrite(yellow_pin, 255);
  } else if (count >= 1) {
    analogWrite(green_pin, 255);
  }

  Serial.print("sound value : ");
  Serial.println(sound_value);

  

  
  delay(100);
}
