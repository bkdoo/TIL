package com.example.student.multimemoproject.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.student.multimemoproject.BasicInfo;

/**
 * Created by student on 2018-11-26.
 */

public class MemoDatabase {

    public static final String TAG = "MemoDatabase";

    //싱글톤 인스턴스
    private static MemoDatabase database;

    public static String TABLE_MEMO = "MEMO";

    public static String TABLE_PHOTO = "PHOTO";

    public static String TABLE_VIDEO = "VIDEO";

    public static String TABLE_VOICE = "VOICE";

    public static String TABLE_HANDWRITING = "HANDWRITING";

    public static int DATABASE_VERSION = 1;

    private DatabaseHelper dbHelper;

    private SQLiteDatabase db;

    private Context context;



    //생성자 호출
    private MemoDatabase(Context context) {
        this.context = context;
    }

    //SQL 실행 메소드
    public boolean execSQL(String SQL) {
        println("\nexecute called.\n");

        try {
            Log.d(TAG, "SQL : " + SQL);
            db.execSQL(SQL);
        } catch (Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
            return false;
        }

        return true;
    }

    //헬퍼클래스 정의
    private class DatabaseHelper extends SQLiteOpenHelper {
        // 생성자 호출 (database이름: MultimediaMemo/memo.db, database버젼: 1)
        public DatabaseHelper(Context context) {
            super(context, BasicInfo.DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("creating database [" + BasicInfo.DATABASE_NAME + "].");


            println("creating table [" + TABLE_MEMO + "].");

            //테이블 존재 시 drop 후 재생성
            String DROP_SQL = "drop table if exists " + TABLE_MEMO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception e) {
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
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            // TABLE_PHOTO
            println("creating table [" + TABLE_PHOTO + "].");

            //테이블 존재 시 drop 후 재생성
            DROP_SQL = "drop table if exists " + TABLE_PHOTO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            CREATE_SQL = "create table " + TABLE_PHOTO + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  URI TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            // 인덱스 생성
            String CREATE_INDEX_SQL = "create index " + TABLE_PHOTO + "_IDX ON " + TABLE_PHOTO + "("
                    + "URI"
                    + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
            }

            // 이하 VIDEO, VOICE, HANDWRITING 테이블 생성 (PHOTO와 동일)
            println("creating table [" + TABLE_VIDEO + "].");

            // drop existing table
            DROP_SQL = "drop table if exists " + TABLE_VIDEO;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            // create table
            CREATE_SQL = "create table " + TABLE_VIDEO + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  URI TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            // create index
            CREATE_INDEX_SQL = "create index " + TABLE_VIDEO + "_IDX ON " + TABLE_VIDEO + "("
                    + "URI"
                    + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
            }

            // TABLE_VOICE
            println("creating table [" + TABLE_VOICE + "].");

            // drop existing table
            DROP_SQL = "drop table if exists " + TABLE_VOICE;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            // create table
            CREATE_SQL = "create table " + TABLE_VOICE + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  URI TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            // create index
            CREATE_INDEX_SQL = "create index " + TABLE_VOICE + "_IDX ON " + TABLE_VOICE + "("
                    + "URI"
                    + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
            }

            // TABLE_HANDWRITING
            println("creating table [" + TABLE_HANDWRITING + "].");

            // drop existing table
            DROP_SQL = "drop table if exists " + TABLE_HANDWRITING;
            try {
                db.execSQL(DROP_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in DROP_SQL", e);
            }

            // create table
            CREATE_SQL = "create table " + TABLE_HANDWRITING + "("
                    + "  _id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "
                    + "  URI TEXT, "
                    + "  CREATE_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP "
                    + ")";
            try {
                db.execSQL(CREATE_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_SQL", e);
            }

            // create index
            CREATE_INDEX_SQL = "create index " + TABLE_HANDWRITING + "_IDX ON " + TABLE_HANDWRITING + "("
                    + "URI"
                    + ")";
            try {
                db.execSQL(CREATE_INDEX_SQL);
            } catch (Exception e) {
                Log.e(TAG, "Exception in CREATE_INDEX_SQL", e);
            }


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


        }
    }

    //싱글톤 인스턴스 생성 메소드
    public static MemoDatabase getInstance(Context context) {
        if (database == null) {
            database = new MemoDatabase(context);
        }

        return database;
    }

    // 데이터베이스 오픈 메소드
    public boolean open() {
        println("opening database [" + BasicInfo.DATABASE_NAME + "]/");
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return true;
    }


    // 데이터베이스 닫기 메소드
    public void close() {
        println("closing database [" + BasicInfo.DATABASE_NAME + "].");
        db.close();

        database = null;
    }

    private void println(String str) {
        Log.d(TAG, str);
    }

    // SQL문 실행 후 cursor로 리턴하는 메소드
    public Cursor rawQuery(String SQL) {
        println("\nexecuteQuery called.\n");

        Cursor c1 = null;
        try {
            c1 = db.rawQuery(SQL, null);
            println("cursor count : " + c1.getCount());
        } catch (Exception ex) {
            Log.e(TAG, "Exception in executeQuery", ex);
        }

        return c1;
    }


}
