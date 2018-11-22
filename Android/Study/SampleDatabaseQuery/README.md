### Helper 클래스를 이용한 안드로이드 데이터베이스 활용

- 테이블이 처음 만들어지는지 혹은 이미 사용하고 있는 상태인지를 구별
- public SQLiteOpenHelper (Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
  - 파라미터 : context객체(this), 데이터베이스 이름, 데이터 조회시 리턴하는 커서를 만들어낼 factory 객체, version 값
  - getReadableDatabase(), get WritableDatabase() : 데이터베이스가 생성, 갱신될 때 콜백 메소드를 호출



#### MainActivity.java - employeeDB 생성

- SQLiteOpenHelper 클래스를 상속하여 새로운 헬퍼 클래스 생성

  ```java
  private class DatabaseHelper extends SQLiteOpenHelper {
      public DatabaseHelper(Context context) {
          super(context, DATABASE_NAME, null, DATABASE_VERSION);
      }
  	
      //데이터베이스 파일이 처음으로 만들어질 때 호출되는 메소드 정의
      @Override
      public void onCreate(SQLiteDatabase db) {
          println("creating table [" + TABLE_NAME + "].");
          try {
              //테이블이 존재하면 drop 후 재생성
              String DROP_SQL = "drop table if exists " + TABLE_NAME;
              db.execSQL(DROP_SQL);
          } catch (Exception e){
              Log.e(TAG, "Exception in DROP_SQL", e);
          }
  		
          //employee 테이블 생성 sql문
          String CREATE_SQL = "create table " + TABLE_NAME + "("
                  + " _id integer PRIMARY KEY autoincrement, "
                  + " name text, "
                  + " age integer, "
                  + " phone text)";
  
          try {
              db.execSQL(CREATE_SQL);
          } catch(Exception ex) {
              Log.e(TAG, "Exception in CREATE_SQL", ex);
          }
  
          println("inserting records. ");
  
          try {
              //데이터 insert
              db.execSQL("insert into " + TABLE_NAME + "(name, age, phone) values ( 'John', 20, '010-7788-1234' );");
              db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Mike', 35, '010-8888-1111');" );
              db.execSQL( "insert into " + TABLE_NAME + "(name, age, phone) values ('Sean', 26, '010-6677-4321');" );
          } catch (Exception e) {
              Log.e(TAG, "Exception in insert SQL", e);
          }
      }
  
      @Override
      public void onOpen(SQLiteDatabase db) {
          println("opened database [ " + DATABASE_NAME + "].");
      }
  
      @Override
      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
          Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ".");
      }
  }
  ```

- 데이터 조회하기

  ```java
  public class MainActivity extends AppCompatActivity {
  
      private TextView status;
  
      public static final String TAG = "MainActivity";
  
      private static String DATABASE_NAME = null;
      private static String TABLE_NAME = "employee";
      private static int DATABASE_VERSION = 1;
      private DatabaseHelper dbHelper;
      private SQLiteDatabase db;
  
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
  
          status = (TextView) findViewById(R.id.status);
          final EditText input01 = (EditText) findViewById(R.id.input01);
  
          Button queryBtn = (Button) findViewById(R.id.queryBtn);
          queryBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DATABASE_NAME = input01.getText().toString();
  				
                  //openDatabase메소드를 호출하여 헬퍼 객체 생성 및 데이터베이스 객체 참조
                  boolean isOpen = openDatabase();
                  if (isOpen){
                      executeRawQuery();
                      executeRawQueryParam();
                  }
              }
          });
  
      }
  
  
  	//헬퍼 객체 생성 및 데이터베이스 객체 참조 메소드
      private boolean openDatabase() {
          println("opening database [" + DATABASE_NAME + "].");
  
          dbHelper = new DatabaseHelper(this);
          db = dbHelper.getWritableDatabase();
  
          return true;
      }
  
      //select문 실행 메소드
      private void executeRawQuery() {
          println("\nexecuteRawQuery called.\n");
  
          Cursor c1 = db.rawQuery("select count(*) as Total from " + TABLE_NAME, null);
          println("cursor count : " + c1.getCount());
  
          c1.moveToNext();
          println("record count : " + c1.getInt(0));
  
          c1.close();
  
      }
  	
      //파라미터를 전달하며 조건부 select문 실행
      private void executeRawQueryParam() {
          println("\nexecuteRawQueryParam called. \n");
  
          String SQL = "select name, age, phone"
                  + " from " + TABLE_NAME
                  + " where age > ?";
  
          String[] args= {"30"};
  
          Cursor c1 = db.rawQuery(SQL, args);
          int recordCount = c1.getCount();
  
          println("cursor count : " + recordCount + "\n");
  
          for (int i = 0; i < recordCount; i++) {
              c1.moveToNext();
              String name = c1.getString(0);
              int age = c1.getInt(1);
              String phone = c1.getString(2);
  
              println("Record #" + i + " : " + name + " , " + age + " , " + phone);
          }
  
          c1.close();
      }
  ```