package com.example.student.my_rpsgame;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Button btnSci, btnRock, btnPap;
    TextView textResult, textHigh, textCurrent;
    ImageView ivMe, ivAI;

    int current;
    int high ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setTitle("가위바위보 게임");

        btnSci = (Button) findViewById(R.id.btnSci);
        btnRock = (Button) findViewById(R.id.btnRock);
        btnPap = (Button) findViewById(R.id.btnPap);

        textResult = (TextView) findViewById(R.id.textResult);
        textHigh = (TextView) findViewById(R.id.textHigh);
        textCurrent = (TextView) findViewById(R.id.textCurrent);

        ivMe = (ImageView) findViewById(R.id.ivMe);
        ivAI = (ImageView) findViewById(R.id.ivAI);
        Intent inIntent = getIntent();
        current= 0;
        high = inIntent.getIntExtra("high",0);

        textHigh.setText("최고 연승 : " + high + "승");
        textCurrent.setText("현재 연승 : " + current + "승");


        btnSci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int me = 1;

                int ai = (int) (Math.random() * 3) + 1;
                ivMe.setImageResource(R.drawable.ga);
                setAIPick(ai);


                int result = RPS(me, ai);
                setResult(result, current, high);





            }
        });
        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int me = 2;

                int ai = (int) (Math.random() * 3) + 1;
                ivMe.setImageResource(R.drawable.ba);
                setAIPick(ai);


                int result = RPS(me, ai);
                setResult(result, current, high);


            }
        });
        btnPap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int me = 3;

                int ai = (int) (Math.random() * 3) + 1;
                ivMe.setImageResource(R.drawable.bo);
                setAIPick(ai);


                int result = RPS(me, ai);
                setResult(result, current, high);


            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void finish() {
        Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
        outIntent.putExtra("high", high);
        setResult(RESULT_OK,outIntent);
        super.finish();
    }

    private int RPS(int me, int ai) {

        if (me == ai) {
            return 0;

        } else if ((me - ai == 1) || (me - ai == -2)) {
            return 1;

        } else {
            return -1;
        }

    }

    private void setResult(int result, int current, int high) {

        if (result == 0) {
            textResult.setText("DRAW");
            textResult.setTextColor(Color.BLACK);
        } else if (result == 1) {
            textResult.setText("WIN :)");
            textResult.setTextColor(Color.BLUE);
            current++;
            textCurrent.setText("현재 연승 : " + current + "승");
        } else {
            textResult.setText("LOSE :(");
            textResult.setTextColor(Color.RED);
            if (current > high) {
                high = current;
            }
            current = 0;
            textHigh.setText("최고 연승 : " + high + "승");
            textCurrent.setText("현재 연승 : " + current + "승");
        }
        this.current = current;
        this.high = high;

    }


    private void setAIPick(int ai) {
        if (ai == 1) {
            ivAI.setImageResource(R.drawable.ga);
        } else if (ai == 2) {
            ivAI.setImageResource(R.drawable.ba);
        } else {
            ivAI.setImageResource(R.drawable.bo);

        }
    }


}
