### 멀티메모 프로젝트 (Do It 안드로이드)

------

#### 멀티메모 앱의 처리 과정

- 메모리스트 조회 : 
  - 메인에서 저장되어 있는 메모가 모두 보이도록 데이터베이스 조회
  - 추가, 수정, 삭제 기능

- 멀티메모 앱의 장점 :
  - 기존의 멀티미디어 요소들이 따로 저장되는 불편함을 해소
  - 여러가지 입력 가능한 기록 중 선택적으로 사용 가능



#### 멀티메모 앱의 단계별 구성

1. 메인 화면, 리스트뷰 구성
2. 메모입력 화면 구성, 메모수정 모드 추가, 데이터베이스 기능 추가, 사진 기능 추가, 텍스트/손글씨 선택 기능 추가
3. 손글씨 기능 추가
4. 음성기능 추가, 동영상 기능 추가



#### 1단계 - 메인 화면 구성하기

- 타이틀, 메모리스트 영역, 버튼 영역으로 구성
- 메모 리스트 하나의 아이템에는 사진, 텍스트, 손글씨, 동영상 아이콘, 음성 아이콘으로 구성
- 버튼 영역에는 새 메모 추가 및 닫기 버튼으로 구성



#### 2단계 - 데이터베이스와 메모 입력화면 구성 및 사진 기능 추가

- SQLiteOpenHelper를 상속하여 사용하는 방식 적용

- MEMO 테이블 생성

  - _id, REG_DATE, CONTENT_TEXT, ID_PHOTO, ID_VIDEO, ID_VOICE, ID_HANDWRITING, CREATE_DATE

- PHOTO, VIDEO, VOICE, HANDWRITING 테이블 생성

  - _id, URI, CREATE_DATE

- MultiMemoProject/db/MemoDatabase.java

  - 데이터베이스가 처음 만들어 질 때 onCreate 메소드를 호출 - SQL 실행
  - 내부 Helper 클래스에서 Table을 생성

  ```java
  // DatabaseHelper 내부 클래스 정의
  private class DatabaseHelper extends SQLiteOpenHelper {
      
      // 생성자 호출 (database이름: MultimediaMemo/memo.db, database버젼: 1)
      public DatabaseHelper(Context context) {
          super(context, BasicInfo.DATABASE_NAME, null, DATABASE_VERSION);
      }
  
      @Override
      public void onCreate(SQLiteDatabase sqLiteDatabase) {
          ...
  		
          //테이블 존재 시 drop 후 재생성
          String DROP_SQL = "drop table if exists " + TABLE_MEMO;
          try {
              db.execSQL(DROP_SQL);
          } catch (Exception e){
              Log.e(TAG, "Exception in DROP_SQL", e);
          }
  
          // TABLE_MEMO 생성 SQL문
          String CREATE_SQL = "create table " + TABLE_MEMO + "("
                  + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                  + "  INPUT_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
                  + "  CONTENT_TEXT TEXT DEFAULT '', "
                  + "  ID_PHOTO INTEGER, "
                  + "  ID_VIDEO INTEGER, "
                  + "  ID_VOICE INTEGER, "
                  + "  ID_HANDWRITING INTEGER, "
                  + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                  + ")";
  
          try {
              db.execSQL(CREATE_SQL);
          } catch (Exception e){
              Log.e(TAG, "Exception in CREATE_SQL", e);
          }
  
          // TABLE_PHOTO
          
          
          DROP_SQL = "drop table if exists " + TABLE_PHOTO;
          try {
              db.execSQL(DROP_SQL);
          } catch(Exception e) {
              Log.e(TAG, "Exception in DROP_SQL", e);
          }
  
          CREATE_SQL = "create table " + TABLE_PHOTO + "("
                  + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                  + "  URI TEXT, "
                  + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                  + ")";
          try {
              db.execSQL(CREATE_SQL);
          } catch(Exception e) {
              Log.e(TAG, "Exception in CREATE_SQL", e);
          }
          
          String CREATE_INDEX_SQL = "create index " + TABLE_PHOTO + "_IDX ON " + 								TABLE_PHOTO + "(" + "URI" + ")";
          try {
              db.execSQL(CREATE_INDEX_SQL);
          } catch(Exception e) {
              Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
          }
  
          
          //VIDEO, VOICE, HANDWRITING 테이블 생성 (PHOTO와 동일)
          .....
     
  }
  ```

- MultiMemoProject/MainActivity.java

  - 테이블에 저장된 데이터를 조회하여 리스트뷰에 보여준다.

  - openDatabase() : 싱글톤 인스턴스를 통해 MemDatabase 객체의 open() 메소드 호출

    ```java
    public void openDatabase(){
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }
    
        mDatabase = MemoDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();
    
        ....
    
    }
    ```

  - loadMemoListData() : 

    - rawQuery 메소드를 통해 Select문을 실행 후 Cursor에 저장
    - moveToNext()를 이용해 행을 하나씩 넘기면서 MemoListItem(array) 객체로 만든 후 어뎁터에 저장
    - recordCount에 Row 수를 저장

    ```java
    public int loadMemoListData() {
        // Select SQL 문
        String SQL = "select _id, INPUT_DATE, CONTENT_TEXT, ID_PHOTO, ID_VIDEO, ID_VOICE, ID_HANDWRITING from MEMO order by INPUT_DATE desc";
    
        int recordCount = -1;
        if (MainActivity.mDatabase != null){
            // SQL문을 실행하고 cursor로 리턴
            Cursor outCursor = MainActivity.mDatabase.rawQuery(SQL);
    		
            // Table의 Row수 리턴
            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");
    
            ....
    
            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();
    			// cursor가 가르키는 각 행의 데이터를 추출
                String memoId = outCursor.getString(0);
                String dateStr = outCursor.getString(1);
                if (dateStr.length() > 10) {
                    dateStr = dateStr.substring(0,10);
                }
    
                String memoStr = outCursor.getString(2);
                String phtoId = outCursor.getString(3);
                ....
    
                // 데이터들을 MemoListItem array에 넣고 Adapter에 저장 
                mMemoListAdapter.addItem(new MemoListItem(memoId, dateStr, memoStr, handwritingId, handwritingUriStr, phtoId, photoUriStr, videoId, videoUriStr, voiceId, voiceUriStr));
    
                // cursor는 garbage collector가 정리해주지 않으므로 close() 실행
                outCursor.close();
                
                //data가 수정되었음을 알림
                mMemoListAdapter.notifyDataSetChanged();
            }
        }
        return recordCount;
    }
    ```

  - onStart() : 

    - openDatabase()와 loadMemoListData() 호출
    - onCreate() 는 객체가 메모리에 만들어질 때 호출되므로 onStart() 안에서 데이터베이스를 로딩하는 것이 좋다

    ```java
    @Override
    protected void onStart() {
        openDatabase();
        loadMemoListData();
    
        super.onStart();
    }
    ```



- MultiMemoProject/MemoListItem.java

  - MemoListItem(...) : Database의 data를 array에 저장하는 메소드

  ```java
  public MemoListItem(String memoId, String memoDate, String memoText,
                      String id_handwriting, String uri_handwriting,
                      String id_photo, String uri_photo,
                      String id_video, String uri_video,
                      String id_voice, String uri_voice
  )
  {
      mId = memoId;
      mData = new String[10];
      mData[0] = memoDate;
      mData[1] = memoText;
      mData[2] = id_handwriting;
      mData[3] = uri_handwriting;
      mData[4] = id_photo;
      mData[5] = uri_photo;
      mData[6] = id_video;
      mData[7] = uri_video;
      mData[8] = id_voice;
      mData[9] = uri_voice;
  }
  ```



- 