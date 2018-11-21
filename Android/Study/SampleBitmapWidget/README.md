### 나인패치 이미지

- 이미지가 늘어나거나 줄어들 때 생기는 왜곡을 해결하는 방법
- 확장자 앞에 .9를 붙여준다.
- 가로, 세로 2픽셀씩 커지며 끝부분의 픽셀은 흰색 또는 검정색으로 지정된다.
- 흰색은 늘어나지 않는 영역, 검정색은 늘어나는 영역



### [비트맵] 버튼

- 배경부분이 이미지인 버튼은 눌렀을 때의 변화가 없어서 구분하기 어렵다.
- 이를 해결하기 위해 그래픽 이미지로 구성된 버튼이 [비트맵] 버튼
- 버튼이 눌렸을 때와 떼어졌을 때를 구분하며, 버튼 클래스로부터 상속받아 정의한다.
- 뷰를 상속하여 새로운 뷰를 만들 때 뷰가 그려지는 두 가지 방법(메소드)
  - onMeasuer() : 스스로 크기를 정할 때
  - onDraw() : 레이아웃에 맞게 그릴 때



#### SampleBitmapWidget 프로젝트

- BtimapButton.java

  ```java
  // 아이콘 리소스 정의
  int iconNormal = R.drawable.bitmap_button_normal;
  int iconClicked = R.drawable.bitmap_button_clicked;
  
  // 아이콘 상태 정의 (STATUS_NORMAL, STATUS_CLICKED)
  int iconStatus = STATUS_NORMAL;
  ```

  - 버튼 기능을 유지한 채 추가 기능을 구현하고 싶을 때 - AppCompatButton 클래스 상속

  ```java
  // 소스 코드에서 new 연산자를 사용하는 생성자
  public BitmapButton(Context context) {
      super(context);
      init();
  }
  // XML레이아웃에 추가한 경우 자동으로 메모리에 객체화되면서 호출
  public BitmapButton(Context context, AttributeSet attrs) {
      super(context, attrs);
      init();
  }
  ```

  - 객체를 초기화 하는 메소드 - init() 정의

    ```java
    public void init() {
        setBackgroundResource(iconNormal); // 배경 이미지 설정
    
        int defaultTextColor = Color.WHITE;
        float defaultTextSize = getResources().getDimension(R.dimen.text_size);
        Typeface defaultTypeface = Typeface.DEFAULT_BOLD;
    
        setTextColor(defaultTextColor); // TextColor 설정 - 흰색
        
        // TextSize 설정 - res/values/dimens.xml의 text_size
        setTextSize(defaultTextSize);  
        setTypeface(defaultTypeface); // font 설정 - Bold
    }
    ```

  * 버튼을 눌렀을 때의 기능 추가 - onTouchEvent() 메소드 재정의

    ```java
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
    
        int action = event.getAction();
    
        switch (action){
            // 버튼을 눌렀을 때 - 배경이미지: iconClicked
            case MotionEvent.ACTION_DOWN:
                setBackgroundResource(this.iconClicked);
                iconStatus = STATUS_CLICKED;
                break;
    
            case MotionEvent.ACTION_OUTSIDE:
            case MotionEvent.ACTION_CANCEL:
                
            // 버튼에서 손을 놓았을 때 - 배경이미지: iconNormal
            case MotionEvent.ACTION_UP:
                setBackgroundResource(this.iconNormal);
                iconStatus = STATUS_NORMAL;
                break;
        }
    	
        // 이미지 혹은 그래픽을 다시 그려내는 메소드 - onDraw()를 호출
        invalidate();
        
        return true;
    }
    ```



- 동작 화면

  - 기본/ 버튼을 놓았을 때 

    ![Screenshot_2018-11-21-15-43-05](C:\Users\student\Pictures\TIL\Android\Study\SimpleBitmapWidget\Screenshot_2018-11-21-15-43-05.png){: width="30%" height="30%"}

  - 버튼을 눌렀을 때

    ![Screenshot_2018-11-21-15-43-09](C:\Users\student\Pictures\TIL\Android\Study\SimpleBitmapWidget\Screenshot_2018-11-21-15-43-09.png)