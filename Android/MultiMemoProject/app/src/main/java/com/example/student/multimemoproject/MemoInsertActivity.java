package com.example.student.multimemoproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.student.multimemoproject.common.TitleBitmapButton;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by student on 2018-11-27.
 */

public class MemoInsertActivity extends Activity {

    public static final String TAG = "MemoInsertActivity";

    RelativeLayout titleLayout;
    TitleBitmapButton titleBackgroundBtn, insert_videoBtn, insert_voiceBtn, insert_textBtn,
            insert_handwritingBtn, insert_dateBtn, insert_saveBtn, insert_cancelBtn;
    LinearLayout contentsLayout, mediaLayout, memoLayout, buttonLayout;
    ImageView insert_photo;
    EditText insert_memoEdit;
    View insert_handwriting;

    Boolean isPhotoCaptured, isVideoRecorded, isVoiceRecorded, isHandwritingMade;
    Boolean isPhotoFileSaved, isPhotoCanceled;
    Calendar mCalendar = Calendar.getInstance();
    String mMemoMode, mMemoId, mMediaPhotoId, mMediaPhotoUri,
            mMediaVideoId, mMediaVideoUri, mMediaVoiceId, mMediaVoiceUri, mMediaHandwritingId, mMediaHandwritingUri;
    int mSelectedContentArray;
    int mChoicedArrayItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo_insert_activity);

        setBottomButtons();
        setMediaLayout();
        setCalendar();

        Intent intent = getIntent();
        mMemoMode = intent.getStringExtra(BasicInfo.MODE_VIEW);
        if (mMemoMode.equals(BasicInfo.MODE_MODIFY) || mMemoMode.equals(BasicInfo.MODE_VIEW)) {
            processIntent(intent);
        }

        insert_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPhotoCaptured || isPhotoFileSaved) {
                    showDialog(BasicInfo.CONTENT_PHOTO_EX);
                } else {
                    showDialog(BasicInfo.CONTENT_PHOTO);
                }
            }
        });
    }

    public void processIntent(Intent intent) {
        mMemoId = intent.getStringExtra(BasicInfo.KEY_MEMO_ID);
        insert_memoEdit.setText(intent.getStringExtra(BasicInfo.KEY_MEMO_TEXT));
        mMediaPhotoId = intent.getStringExtra(BasicInfo.KEY_ID_PHOTO);
        mMediaPhotoUri = intent.getStringExtra(BasicInfo.KEY_URI_PHOTO);
        mMediaVideoId = intent.getStringExtra(BasicInfo.KEY_ID_VIDEO);
        mMediaVideoUri = intent.getStringExtra(BasicInfo.KEY_URI_VIDEO);
        mMediaVoiceId = intent.getStringExtra(BasicInfo.KEY_ID_VOICE);
        mMediaVoiceUri = intent.getStringExtra(BasicInfo.KEY_URI_VOICE);
        mMediaHandwritingId = intent.getStringExtra(BasicInfo.KEY_ID_HANDWRITING);
        mMediaHandwritingUri = intent.getStringExtra(BasicInfo.KEY_URI_HANDWRITING);

        setMediaImage(mMediaPhotoId, mMediaPhotoUri, mMediaVideoId, mMediaVoiceId, mMediaHandwritingId);
    }

    private void setMediaImage(String photoId, String photoUri, String videoId, String voiceId, String handwritingId) {
        Log.d(TAG, "photoId : " + photoId + "photoUri : " + photoUri);

        if (photoId.equals("") || photoId.equals("-1")) {
            insert_photo.setImageResource(R.drawable.person_add);
        } else {
            isPhotoFileSaved = true;
            insert_photo.setImageURI(Uri.parse(BasicInfo.FOLDER_PHOTO + photoUri));
        }

    }

    private void setBottomButtons() {
        insert_saveBtn = (TitleBitmapButton) findViewById(R.id.insert_saveBtn);
        insert_cancelBtn = (TitleBitmapButton) findViewById(R.id.insert_cancelBtn);

        insert_cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        insert_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    private void setMediaLayout() {
        isPhotoCaptured = false;
        isVideoRecorded = false;
        isVoiceRecorded = false;
        isHandwritingMade = false;

        insert_videoBtn = (TitleBitmapButton) findViewById(R.id.insert_videoBtn);
        insert_voiceBtn = (TitleBitmapButton) findViewById(R.id.insert_voiceBtn);
    }

    private void setCalendar() {
        insert_dateBtn = (TitleBitmapButton) findViewById(R.id.insert_dateBtn);
        insert_dateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mDateStr = insert_dateBtn.getText().toString();
                Calendar calendar = Calendar.getInstance();
                Date date = new Date();
                try {
                    date = BasicInfo.dateDayNameFormat.parse(mDateStr);
                } catch (Exception e) {
                    Log.e(TAG, "Exception in parsing date : " + date, e);
                }

                calendar.setTime(date);

                new DatePickerDialog(MemoInsertActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Date curDate = new Date();
        mCalendar.setTime(curDate);

        int year = mCalendar.get(Calendar.YEAR);
        int monthOfYear = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        insert_dateBtn.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mCalendar.set(year, monthOfYear, dayOfMonth);
            insert_dateBtn.setText(year + "년 " + (monthOfYear + 1) + "월 " + dayOfMonth + "일");
        }
    };

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = null;

        switch (id) {
            case BasicInfo.CONFIRM_TEXT_INPUT:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("메모");
                builder.setMessage("텍스트를 입력하세요.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                break;

            case BasicInfo.CONTENT_PHOTO:
                builder = new AlertDialog.Builder(this);

                mSelectedContentArray = R.array.array_photo;
                builder.setTitle("선택하세요");
                builder.setSingleChoiceItems(mSelectedContentArray, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mChoicedArrayItem = whichButton;
                    }
                });
                builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (mChoicedArrayItem == 0) {
                            showPhotoCaptureActivity();
                        } else if (mChoicedArrayItem == 1) {
                            showPhotoSelectionActivity();
                        }
                    }
                });

            case BasicInfo.CONTENT_PHOTO_EX:
                builder = new AlertDialog.Builder(this);

                mSelectedContentArray = R.array.array_photo_ex;
                builder.setTitle("선택하세요");
                builder.setSingleChoiceItems(mSelectedContentArray, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        mChoicedArrayItem = whichButton;
                    }
                });
                builder.setPositiveButton("선택", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if(mChoicedArrayItem == 0) {
                            showPhotoCaptureActivity();
                        } else if(mChoicedArrayItem == 1) {
                            showPhotoSelectionActivity();
                        } else if(mChoicedArrayItem == 2) {
                            isPhotoCanceled = true;
                            isPhotoCaptured = false;

                            insert_photo.setImageResource(R.drawable.person_add);
                        }
                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                break;

            default:
                break;
        }

        return builder.create();
    }

    private void showPhotoSelectionActivity() {
        Intent intent = new Intent(getApplicationContext(), PhotoCaptureActivity.class);
        startActivityForResult(intent, BasicInfo.REQ_PHOTO_CAPTURE_ACTIVITY);
    }

    private void showPhotoCaptureActivity() {
        Intent intent = new Intent(getApplicationContext(), PhotoSelectionActivity.class);
        startActivityForResult(intent, BasicInfo.REQ_PHOTO_SELECTION_ACTIVITY);
    }
}
