## 아두이노 - LCD

#### LCD

- 액정 화면에 글자나 숫자를 표시
- 주로 2줄, 16글자를 표시할 수 있는 1602 LCD를 많이 사용
- LiquidCrystal 라이브러리 사용



#### 1602 LCD + I2C

- 1602 LCD는 16개의 핀으로 구성되어있어 연결시 복잡함과 추가 장치 연결에 어려움이 많음

- 이를 해결해주기 위한 I2C 장치를 사용

- 1602 LCD + I2C 모듈을 사용하기 위해 LiquidCrystal_I2C 라이브러리 사용

- LiquidCrystal_I2C 라이브러리의 함수들

  | 함수                      | 설명                               |
  | ------------------------- | ---------------------------------- |
  | lcd.backlight();          | LCD 백라이트를 켠다                |
  | lcd.noBacklight();        | LCD 백라이트를 끈다                |
  | lcd.noDisplay();          | LCD 표시된 내용을 숨긴다           |
  | lcd.display();            | LCD 표시 내용을 보여준다           |
  | lcd.cursor();             | LCD 커서를 표시한다                |
  | lcd.noCursor();           | LCD 커서를 없앤다                  |
  | lcd,setCursor(x,y);       | LCD 해당 LCD 좌표(x, y)로 커서이동 |
  | lcd.home();               | 커서를 (0, 0)좌표로 이동           |
  | lcd.blink();              | 커서를 깜빡임                      |
  | lcd.noBlink();            | 커서를 깜빡이지 않음               |
  | lcd.write(36);            | 아스키코드 값으로 문자를 출력      |
  | lcd.print("Hello World"); | LCD 화면에 값을 출력               |
  | lcd.clear();              | LCD 모든 내용 지움                 |
  | lcd.scrollDisplayRight(); | LCD 내용을 우측으로 1칸 스크롤     |
  | lcd.scrollDisplayLeft();  | LCD 내용을 좌측으로 1칸 스크롤     |

