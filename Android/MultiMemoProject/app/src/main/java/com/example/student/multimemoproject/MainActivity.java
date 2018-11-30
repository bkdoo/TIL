package com.example.student.multimemoproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.multimemoproject.common.TitleBitmapButton;
import com.example.student.multimemoproject.db.MemoDatabase;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MultiMemoActivity";


    ListView mMemoListView;
    MemoListAdapter mMemoListAdapter;
    int mMemoCount = 0;
    TextView itemCount;

    public static MemoDatabase mDatabase = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // SD Card 체크
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "SD 카드가 없습니다. SD 카드를 넣은 후 다시 실행하십시오.", Toast.LENGTH_LONG).show();
            return;
        } else {
            String externalPath = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!BasicInfo.ExternalChecked && externalPath != null) {
                BasicInfo.ExternalPath = externalPath + File.separator;
                Log.d(TAG, "ExternalPath : " + BasicInfo.ExternalPath);

                BasicInfo.FOLDER_PHOTO = BasicInfo.ExternalPath + BasicInfo.FOLDER_PHOTO;
                BasicInfo.FOLDER_VIDEO = BasicInfo.ExternalPath + BasicInfo.FOLDER_VIDEO;
                BasicInfo.FOLDER_VOICE = BasicInfo.ExternalPath + BasicInfo.FOLDER_VOICE;
                BasicInfo.FOLDER_HANDWRITING = BasicInfo.ExternalPath + BasicInfo.FOLDER_HANDWRITING;
                BasicInfo.DATABASE_NAME = BasicInfo.ExternalPath + BasicInfo.DATABASE_NAME;

                BasicInfo.ExternalChecked = true;
            }
        }

        // 메모 리스트
        mMemoListView = (ListView) findViewById(R.id.memoList);
        mMemoListAdapter = new MemoListAdapter(this);
        mMemoListView.setAdapter(mMemoListAdapter);
        //리스트뷰의 한 아이템 클릭시 viewMemo 메소드 호출
        mMemoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                viewMemo(position);
            }
        });

        // 새 메모 버튼 설정
        TitleBitmapButton newMemoBtn = (TitleBitmapButton) findViewById(R.id.newMemoBtn);
        newMemoBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "newMemoBtn clicked.");

                // MemoInsertActivity에 메모 생성/ 수정 중 메모 생성 인텐트를 전달
                Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
                intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_INSERT);
                startActivityForResult(intent, BasicInfo.REQ_INSERT_ACTIVITY);
            }
        });

        // 닫기 버튼 설정
        TitleBitmapButton closeBtn = (TitleBitmapButton) findViewById(R.id.closeBtn);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        itemCount = (TextView) findViewById(R.id.itemCount);

        //위험권한 체크 메소드 호출
        checkDangerousPermissions();

    }

    //4가지 위험 권한 체크 메소드
    private void checkDangerousPermissions() {
        String[] permissions = {
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.RECORD_AUDIO
        };

        int permissionCheck = PackageManager.PERMISSION_GRANTED;
        for (int i = 0; i < permissions.length; i++) {
            permissionCheck = ContextCompat.checkSelfPermission(this, permissions[i]);
            if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                break;
            }
        }

        //권한 유무에 따른 Toast 메세지 전달
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this, permissions, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    @Override
    protected void onStart() {
        // 데이터베이스 열기 메소드
        openDatabase();
        // 메모 데이터 로드 메소드
        loadMemoListData();

        super.onStart();
    }

    // 데이터베이스 열기 (Helper클래스를 이용)
    public void openDatabase() {
        //이전에 열려있는 데이터베이스 닫기
        if (mDatabase != null) {
            mDatabase.close();
            mDatabase = null;
        }

        //싱글톤을 이용하여 데이터베이스 열기
        mDatabase = MemoDatabase.getInstance(this);
        boolean isOpen = mDatabase.open();

        if (isOpen) {
            Log.d(TAG, "Memo database is open.");
        } else {
            Log.d(TAG, "Memo database is not open.");
        }

    }

    // 메모 리스트 로딩 메소드
    public int loadMemoListData() {
        // 메모 리스트 조회 SQL문
        String SQL = "select _id, INPUT_DATE, CONTENT_TEXT, ID_PHOTO, ID_VIDEO, ID_VOICE, ID_HANDWRITING from MEMO order by INPUT_DATE desc";

        int recordCount = -1;
        if (MainActivity.mDatabase != null) {
            // SQL문을 실행하고 cursor로 리턴
            Cursor outCursor = MainActivity.mDatabase.rawQuery(SQL);

            // Table의 Row수 리턴
            recordCount = outCursor.getCount();
            Log.d(TAG, "cursor count : " + recordCount + "\n");

            mMemoListAdapter.clear();
            Resources res = getResources();


            for (int i = 0; i < recordCount; i++) {
                outCursor.moveToNext();

                // cursor가 가르키는 각 행의 데이터를 추출
                String memoId = outCursor.getString(0);

                String dateStr = outCursor.getString(1);
                if (dateStr.length() > 10) {
                    dateStr = dateStr.substring(0, 10);
                }

                String memoStr = outCursor.getString(2);
                String phtoId = outCursor.getString(3);
                String photoUriStr = getPhotoUriStr(phtoId);

                String videoId = outCursor.getString(4);
                String videoUriStr = null;

                String voiceId = outCursor.getString(5);
                String voiceUriStr = null;

                String handwritingId = outCursor.getString(6);
                String handwritingUriStr = null;

                handwritingUriStr = getHandwritingUriStr(handwritingId);

                videoUriStr = getVideoUriStr(videoId);
                voiceUriStr = getVoiceUriStr(voiceId);

                // 데이터들을 MemoListItem array에 넣고 Adapter에 저장
                mMemoListAdapter.addItem(new MemoListItem(memoId, dateStr, memoStr, handwritingId, handwritingUriStr, phtoId, photoUriStr,
                        videoId, videoUriStr, voiceId, voiceUriStr));
            }
            // cursor는 garbage collector가 정리해주지 않으므로 close() 실행
            outCursor.close();
            //dataSet이 수정되었음을 알림
            mMemoListAdapter.notifyDataSetChanged();

            //텍스트 뷰에 리스트의 아이템 수 표시
            itemCount.setText(recordCount + " " + getResources().getString(R.string.item_count));
            itemCount.invalidate();
        }
        return recordCount;
    }

    //사진 데이터 Uri 가져오는 메소드
    private String getPhotoUriStr(String id_photo) {
        String photoUriStr = null;
        if (id_photo != null && !id_photo.equals("-1")) {
            //해당 리스트의 사진 id를 이용하여 select문을 통해 URI 조회
            String SQL = "select URI from " + MemoDatabase.TABLE_PHOTO + " where _ID = " + id_photo + "";
            //조회된 행을 cursor로 리턴
            Cursor photoCursor = MainActivity.mDatabase.rawQuery(SQL);
            if (photoCursor.moveToNext()) {
                photoUriStr = photoCursor.getString(0);
            }
            photoCursor.close();
        } else if (id_photo == null || id_photo.equals("-1")) {
            photoUriStr = "";
        }

        return photoUriStr;
    }

    //손글씨 데이터 Uri 가져오는 메소드 (동작 방식은 getPhotoUriStr과 같음)
    public String getHandwritingUriStr(String id_handwriting) {
        Log.d(TAG, "Handwriting ID : " + id_handwriting);

        String handwritingUriStr = null;
        if (id_handwriting != null && id_handwriting.trim().length() > 0 && !id_handwriting.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_HANDWRITING + " where _ID = " + id_handwriting + "";
            Cursor handwritingCursor = MainActivity.mDatabase.rawQuery(SQL);
            if (handwritingCursor.moveToNext()) {
                handwritingUriStr = handwritingCursor.getString(0);
            }
            handwritingCursor.close();
        } else {
            handwritingUriStr = "";
        }

        return handwritingUriStr;
    }

    //비디오 데이터 Uri 가져오는 메소드 (동작 방식은 getPhotoUriStr과 같음)
    public String getVideoUriStr(String id_video) {
        Log.d(TAG, "Video ID : " + id_video);

        String videoUriStr = null;
        if (id_video != null && id_video.trim().length() > 0 && !id_video.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_VIDEO + " where _ID = " + id_video + "";
            Cursor videoCursor = MainActivity.mDatabase.rawQuery(SQL);
            if (videoCursor.moveToNext()) {
                videoUriStr = videoCursor.getString(0);
            }
            videoCursor.close();
        } else {
            videoUriStr = "";
        }

        return videoUriStr;
    }

    //음성 데이터 Uri 가져오는 메소드 (동작 방식은 getPhotoUriStr과 같음)
    public String getVoiceUriStr(String id_voice) {
        Log.d(TAG, "Voice ID : " + id_voice);

        String voiceUriStr = null;
        if (id_voice != null && id_voice.trim().length() > 0 && !id_voice.equals("-1")) {
            String SQL = "select URI from " + MemoDatabase.TABLE_VOICE + " where _ID = " + id_voice + "";
            Cursor voiceCursor = MainActivity.mDatabase.rawQuery(SQL);
            if (voiceCursor.moveToNext()) {
                voiceUriStr = voiceCursor.getString(0);
            }
            voiceCursor.close();
        } else {
            voiceUriStr = "";
        }

        return voiceUriStr;
    }


    private void viewMemo(int position) {
        MemoListItem item = (MemoListItem) mMemoListAdapter.getItem(position);

        // 메모 보기 액티비티 띄우기
        Intent intent = new Intent(getApplicationContext(), MemoInsertActivity.class);
        intent.putExtra(BasicInfo.KEY_MEMO_MODE, BasicInfo.MODE_VIEW);
        intent.putExtra(BasicInfo.KEY_MEMO_ID, item.getId());
        intent.putExtra(BasicInfo.KEY_MEMO_DATE, item.getData(0));
        intent.putExtra(BasicInfo.KEY_MEMO_TEXT, item.getData(1));
        intent.putExtra(BasicInfo.KEY_ID_HANDWRITING, item.getData(2));
        intent.putExtra(BasicInfo.KEY_URI_HANDWRITING, item.getData(3));
        intent.putExtra(BasicInfo.KEY_ID_PHOTO, item.getData(4));
        intent.putExtra(BasicInfo.KEY_URI_PHOTO, item.getData(5));
        intent.putExtra(BasicInfo.KEY_ID_VIDEO, item.getData(6));
        intent.putExtra(BasicInfo.KEY_URI_VIDEO, item.getData(7));
        intent.putExtra(BasicInfo.KEY_ID_VOICE, item.getData(8));
        intent.putExtra(BasicInfo.KEY_ID_VOICE, item.getData(9));

        startActivityForResult(intent, BasicInfo.REQ_VIEW_ACTIVITY);

    }


    //다른 액티비티의 응답 처리 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case BasicInfo.REQ_INSERT_ACTIVITY:
                if (requestCode == RESULT_OK) {
                    loadMemoListData();
                }
                break;
            case BasicInfo.REQ_VIEW_ACTIVITY:
                loadMemoListData();
                break;
        }
    }

}
