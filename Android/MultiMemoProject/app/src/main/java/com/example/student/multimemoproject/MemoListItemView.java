package com.example.student.multimemoproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by student on 2018-11-26.
 */

public class MemoListItemView extends LinearLayout {
    private ImageView itemPhoto;

    private TextView itemDate;

    private TextView itemText;

    private ImageView itemVideoState;

    private ImageView itemVoiceState;

    private ImageView itemHandwriting;

    private Context mContext;

    private String mVideoUri;

    private String mVoiceUri;

    Bitmap bitmap;


    //리스트 하나당의 뷰를 설정하는 메소드
    public MemoListItemView(Context context) {
        super(context);
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.memo_listitem, this, true);

        itemPhoto = (ImageView) findViewById(R.id.itemPhoto);

        itemDate = (TextView) findViewById(R.id.itemDate);

        itemText = (TextView) findViewById(R.id.itemText);

        itemHandwriting = (ImageView) findViewById(R.id.itemHandwriting);

        itemVideoState = (ImageView) findViewById(R.id.itemVideoState);
        itemVideoState.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (mVideoUri != null && mVideoUri.trim().length() > 0 && !mVideoUri.equals("-1")) {
                    showVideoPlayingActivity();
                } else {
                    Toast.makeText(mContext, "재생할 동영상이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        itemVoiceState = (ImageView) findViewById(R.id.itemVoiceState);
        itemVoiceState.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (mVoiceUri != null && mVoiceUri.trim().length() > 0 && !mVoiceUri.equals("-1")) {
                    showVoicePlayingActivity();
                } else {
                    Toast.makeText(mContext, "재생할 음성이 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //음성 재생 액티비티 실행 메소드
    public void showVoicePlayingActivity() {
        Intent intent = new Intent(mContext, VoicePlayActivity.class);
        intent.putExtra(BasicInfo.KEY_URI_VOICE, BasicInfo.FOLDER_VOICE + mVoiceUri);

        mContext.startActivity(intent);
    }

    //영상 재생 액티비티 실행 메소드
    public void showVideoPlayingActivity() {
        Intent intent = new Intent(mContext, VideoPlayActivity.class);
        if (BasicInfo.isAbsoluteVideoPath(mVideoUri)) {
            intent.putExtra(BasicInfo.KEY_URI_VIDEO, BasicInfo.FOLDER_VIDEO + mVideoUri);
        } else {
            intent.putExtra(BasicInfo.KEY_URI_VIDEO, mVideoUri);
        }

        mContext.startActivity(intent);
    }

    //리스트에 미리보기 설정하는 메소드
    public void setContents(int index, String data) {
        if (index == 0) {
            itemDate.setText(data);
        } else if (index == 1) {
            itemText.setText(data);
        } else if (index == 2) {
            if (data == null || data.equals("-1") || data.equals("")) {
                itemHandwriting.setImageBitmap(null);
            } else {
                itemHandwriting.setImageURI(Uri.parse(BasicInfo.FOLDER_HANDWRITING + data));
            }
        } else if (index == 3) {
            if (data == null || data.equals("-1") || data.equals("")) {
                itemPhoto.setImageResource(R.drawable.person);
            } else {
                if (bitmap != null) {
                    bitmap.recycle();
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 8;
                bitmap = BitmapFactory.decodeFile(BasicInfo.FOLDER_PHOTO + data, options);

                itemPhoto.setImageBitmap(bitmap);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    //음성, 영상 유무에 따른 아이콘 설정 메소드
    public void setMediaState(String sVideoUri, String sVoiceUri) {
        mVideoUri = sVideoUri;
        mVoiceUri = sVoiceUri;
        if(sVideoUri == null || sVideoUri.trim().length() < 1 || sVideoUri.equals("-1")) {
            itemVideoState.setImageResource(R.drawable.icon_video_empty);
        } else {
            itemVideoState.setImageResource(R.drawable.icon_video);
        }

        if(sVoiceUri == null || sVoiceUri.trim().length() < 1 || sVoiceUri.equals("-1")) {
            itemVoiceState.setImageResource(R.drawable.icon_voice_empty);
        } else {
            itemVoiceState.setImageResource(R.drawable.icon_voice);
        }
    }
}
