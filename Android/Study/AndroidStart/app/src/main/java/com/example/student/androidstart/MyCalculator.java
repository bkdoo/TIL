package com.example.student.androidstart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Stack;

public class MyCalculator extends AppCompatActivity {

    Button btnClear, btnDot, btnEq, btnPlus, btnMinus, btnDiv, btnMulti;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    EditText edt;
    float result;
    ArrayList<String> list;
    ArrayList<String> list2;
    TextView tvResult;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calculator);
        setTitle("My Calculator");

        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);

        btnClear = (Button) findViewById(R.id.btnClear);
        btnDot = (Button) findViewById(R.id.btnDot);
        btnEq = (Button) findViewById(R.id.btnEq);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMulti = (Button) findViewById(R.id.btnMulti);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        edt = (EditText) findViewById(R.id.edt);
        tvResult= (TextView) findViewById(R.id.tvResult);
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        result = 0;


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText("");
                result = 0;
                tvResult.setText("");
                list.clear();
                list2.clear();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "0");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + "9");
            }
        });
        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText(edt.getText() + ".");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().equals("")) {
                    list.add("0");
                } else {
                    list.add(edt.getText().toString());
                }
                list.add("+");
                edt.setText("");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().equals("")) {
                    list.add("0");
                } else {
                    list.add(edt.getText().toString());
                }
                list.add("-");
                edt.setText("");
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().equals("")) {
                    list.add("0");
                } else {
                    list.add(edt.getText().toString());
                }
                list.add("*");
                edt.setText("");
            }
        });
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().equals("")) {
                    list.add("0");
                } else {
                    list.add(edt.getText().toString());
                }
                list.add("/");
                edt.setText("");
            }
        });
        btnEq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt.getText().equals("")) {
                    list.add("0");
                } else {
                    list.add(edt.getText().toString());
                }

                toPost(list);
                calculate(list2);
                tvResult.setText("결과 : " + result);
                list.clear();
                list2.clear();
                edt.setText(String.valueOf(result));
            }
        });
    }

    private void calculate(ArrayList<String> list2) {
        Stack<String> stack = new Stack<>();
        for (String s : list2) {
            if (s.equals("+")) {
                if (!stack.isEmpty()) {
                    float a = Float.valueOf(stack.pop());
                    float b = Float.valueOf(stack.pop());
                    stack.push(String.valueOf(a + b));
                }
            } else if (s.equals("-")) {
                if (!stack.isEmpty()) {
                    float a = Float.valueOf(stack.pop());
                    float b = Float.valueOf(stack.pop());
                    stack.push(String.valueOf(b - a));
                }
            } else if (s.equals("*")) {
                if (!stack.isEmpty()) {
                    float a = Float.valueOf(stack.pop());
                    float b = Float.valueOf(stack.pop());
                    stack.push(String.valueOf(a * b));
                }
            } else if (s.equals("/")) {
                if (!stack.isEmpty()) {
                    float a = Float.valueOf(stack.pop());
                    float b = Float.valueOf(stack.pop());
                    stack.push(String.valueOf(b / a));
                }
            } else {
                stack.push(s);
            }

        }
        if (!stack.isEmpty()) {
            result = Float.valueOf(stack.pop());
        }
    }

    private void toPost(ArrayList<String> list) {
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
                if (!stack.empty()) {
                    list2.add(stack.pop());
                }
                stack.push(s);
            } else {
                list2.add(s);
            }
        }

        if (!stack.empty()) {
            list2.add(stack.peek());
        }

    }
}
