#include <dht11.h>

#include <SoftwareSerial.h>






int count = 0;

int DHT11PIN = A0;            //Signal 이 연결된 아두이노의 핀번호
 
dht11 DHT11;


 

SoftwareSerial bluetooth(2, 3);


void setup() {
  Serial.begin(9600);
  bluetooth.begin(9600);
 
  
}

void loop() {
  int chk = DHT11.read(DHT11PIN);
  float T = (float)DHT11.temperature;
  float RH = (float)DHT11.humidity;
  
  char val = bluetooth.read();

  

  

  if (bluetooth.available()) {       
    Serial.write(bluetooth.read());  //블루투스측 내용을 시리얼모니터에 출력
  }
  if (Serial.available()) {         
    bluetooth.write(Serial.read());  //시리얼 모니터 내용을 블루추스 측에 WRITE
  }
  
  

  

 

  

  
  delay(100);
}
