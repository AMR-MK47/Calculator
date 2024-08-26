package com.example.calculator;

import static com.example.calculator.Result.divisionByZero;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private TextView ResultText;
    private boolean isDarkMode = false;
    private ConstraintLayout mainLayout;
    private Button toggleDarkModeButton;
    private Button AcBtn;
    private ImageButton ModelusBtn;
    private ImageButton RemoveBtn;
    private ImageButton DividBtn;
    private Button SevenBtn;
    private Button EightBtn;
    private Button NineBtn;
    private Button ZeroBtn;
    private ImageButton DotBtn;
    private ImageButton EqualBtn;
    private ImageButton PlusBtn;
    private ImageButton MinusBtn;
    private ImageButton MultiplyBtn;
    private Button FiveBtn;
    private Button SixBtn;
    private Button FourBtn;
    private Button ThreeBtn;
    private Button TwoBtn;
    private Button OneBtn;
    private Button DzeroBtn;
    private String expression;
    private boolean result_displayed = false;
    private String currentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResultText = findViewById(R.id.TextResulte);
        ZeroBtn = findViewById(R.id.Btn0);
        SixBtn = findViewById(R.id.Btn6);
        DotBtn = findViewById(R.id.BtnDot);
        EqualBtn = findViewById(R.id.BtnEqual);
        TwoBtn = findViewById(R.id.Btn2);
        PlusBtn = findViewById(R.id.BtnPlus);
        ThreeBtn = findViewById(R.id.Btn3);
        DzeroBtn = findViewById(R.id.BtnInvert);
        FiveBtn = findViewById(R.id.Btn5);
        OneBtn = findViewById(R.id.Btn1);
        MinusBtn = findViewById(R.id.BtnMinus);
        MultiplyBtn = findViewById(R.id.BtnMultiply);
        FourBtn = findViewById(R.id.Btn4);
        SevenBtn = findViewById(R.id.Btn7);
        NineBtn = findViewById(R.id.Btn9);
        EightBtn = findViewById(R.id.Btn8);
        DividBtn = findViewById(R.id.BtnDivid);
        AcBtn = findViewById(R.id.BtnAc);
        ModelusBtn = findViewById(R.id.BtnModelus);
        mainLayout = findViewById(R.id.main);
        RemoveBtn = findViewById(R.id.BtnRemove);
        toggleDarkModeButton = findViewById(R.id.toggleDarkModeButton);
        toggleDarkModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDarkMode();
            }
        });
        AcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultText.setText("0");
                ResultText.setTextSize(100);
            }
        });
        DotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = ResultText.getText().toString();
                if (!currentText.equals("0") && !result_displayed) {
                    if (currentText.length() > 0) {
                        char lastChar = currentText.charAt(currentText.length() - 1);
                        // Check if the last character is a digit or closing parenthesis
                        if (Character.isDigit(lastChar) || lastChar == ')') {
                            if (!currentText.contains(".")) {
                                ResultText.append(".");
                                adjustTextSize();
                            }
                        }
                    }
                }
                result_displayed = false;
            }
        });
        RemoveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentText = currentText = ResultText.getText().toString();
                if (currentText.length() > 1) {
                    ResultText.setText(currentText.substring(0, currentText.length() - 1));
                } else {
                    ResultText.setText("0");
                }
                adjustTextSize();
            }

        });
        EqualBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                expression = ResultText.getText().toString();
                if (!Result.isValidExpression(expression)) {
                    ResultText.setText("Invalid expression");
                    adjustTextSize();
                    return;
                }

                double result = Result.evaluateExpression(expression);

                if (Result.divisionByZero) {
                    ResultText.setText("Error");
                    Toast.makeText(MainActivity.this, "You Can't Divide by Zero", Toast.LENGTH_SHORT).show();
                    Result.divisionByZero = false;
                } else {
                    ResultText.setText(String.valueOf(result));
                }

                adjustTextSize();
                result_displayed = true;
            }
        });
        PlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("+");
            }
        });
        MinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("-");
            }
        });
        MultiplyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("×");
            }
        });
        DividBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("÷");
            }
        });
        ModelusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOperator("%");
            }
        });
        DzeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ResultText.getText().toString().equals("0") && !result_displayed) {
                    ResultText.append("00");
                }
                adjustTextSize();
            }
        });
        ZeroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ResultText.getText().toString().equals("0") && !result_displayed) {
                    ResultText.append("0");
                }
                adjustTextSize();
            }

        });
        OneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("1");
                    result_displayed = false;
                } else {
                    ResultText.append("1");
                }
                adjustTextSize();
            }
        });
        TwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("2");
                    result_displayed = false;
                } else {
                    ResultText.append("2");
                }
                adjustTextSize();
            }
        });
        ThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("3");
                    result_displayed = false;
                } else {
                    ResultText.append("3");
                }
                adjustTextSize();
            }
        });
        FourBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("4");
                    result_displayed = false;
                } else {
                    ResultText.append("4");
                }
                adjustTextSize();
            }
        });
        FiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("5");
                    result_displayed = false;
                } else {
                    ResultText.append("5");
                }
                adjustTextSize();
            }
        });
        SixBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("6");
                    result_displayed = false;
                } else {
                    ResultText.append("6");
                }
                adjustTextSize();
            }
        });
        SevenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("7");
                    result_displayed = false;
                } else {
                    ResultText.append("7");
                }
                adjustTextSize();
            }
        });
        EightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("8");
                    result_displayed = false;
                } else {
                    ResultText.append("8");
                }
                adjustTextSize();
            }
        });
        NineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ResultText.getText().toString().equals("0") || result_displayed) {
                    ResultText.setText("9");
                    result_displayed = false;
                } else {
                    ResultText.append("9");
                }
                adjustTextSize();
            }
        });
    };
    private void handleOperator(String operator) {
        String currentText = ResultText.getText().toString();
        if (!currentText.equals("0") && !result_displayed) {
            if (currentText.length() > 0) {
                char lastChar = currentText.charAt(currentText.length() - 1);
                // Check if the last character is a digit or closing parenthesis
                if (Character.isDigit(lastChar) || lastChar == ')') {
                    ResultText.append(operator);
                    adjustTextSize();
                }
            }
        }
        result_displayed = false;
    }
    private void adjustTextSize() {
        int length = ResultText.getText().toString().length();
        float textSize;

        if (length <= 6) {
            textSize = 100; // حجم الخط الافتراضي
        } else if (length <= 8) {
            textSize = 80; // الحجم التالي
        } else if (length <= 11) {
            textSize = 60; // الحجم التالي
        } else if (length <= 17) {
            textSize = 40; // الحجم التالي
        } else if (length <= 23) {
            textSize = 30; // الحجم التالي
        } else {
            textSize = 20; // الحجم الأصغر للنصوص الطويلة
        }

        ResultText.setTextSize(textSize);
    }

    @SuppressLint("ResourceType")
    private void toggleDarkMode() {
        if (isDarkMode) {
            // Switch to Light Mode
            AcBtn.setBackgroundResource(R.drawable.ac_btn_l);
            MinusBtn.setBackgroundResource(R.drawable.mines_btn_l);
            PlusBtn.setBackgroundResource(R.drawable.plus_btn_l);
            EqualBtn.setBackgroundResource(R.drawable.equal_btn_l);
            DotBtn.setBackgroundResource(R.drawable.btn_light);
            DotBtn.setImageResource(R.drawable.dot_l);
            DzeroBtn.setBackgroundResource(R.drawable.btn_light);
            ZeroBtn.setBackgroundResource(R.drawable.btn_light);
            OneBtn.setBackgroundResource(R.drawable.btn_light);
            TwoBtn.setBackgroundResource(R.drawable.btn_light);
            ThreeBtn.setBackgroundResource(R.drawable.btn_light);
            SixBtn.setBackgroundResource(R.drawable.btn_light);
            FiveBtn.setBackgroundResource(R.drawable.btn_light);
            FourBtn.setBackgroundResource(R.drawable.btn_light);
            MultiplyBtn.setBackgroundResource(R.drawable.mul_btn_l);
            NineBtn.setBackgroundResource(R.drawable.btn_light);
            EightBtn.setBackgroundResource(R.drawable.btn_light);
            SevenBtn.setBackgroundResource(R.drawable.btn_light);
            DividBtn.setBackgroundResource(R.drawable.div_btn_l);
            RemoveBtn.setBackgroundResource(R.drawable.rem_btn_l);
            ModelusBtn.setBackgroundResource(R.drawable.med_btn_l);
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackground));
            ResultText.setTextColor((ContextCompat.getColor(this, R.color.Result)));
            toggleDarkModeButton.setBackgroundResource(R.drawable.dark_mode_24);
            OneBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            TwoBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            ThreeBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            FourBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            FiveBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            SixBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            SevenBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            EightBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            NineBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            DzeroBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
            ZeroBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_l)));
        } else {
            // Switch to Dark Mode
            DotBtn.setBackgroundResource(R.drawable.btn);
            DotBtn.setImageResource(R.drawable.dot_d);
            EqualBtn.setBackgroundResource(R.drawable.equal_btn_d);
            ZeroBtn.setBackgroundResource(R.drawable.btn);
            DzeroBtn.setBackgroundResource(R.drawable.btn);
            PlusBtn.setBackgroundResource(R.drawable.plus_btn_d);
            MinusBtn.setBackgroundResource(R.drawable.mines_btn_d);
            FiveBtn.setBackgroundResource(R.drawable.btn);
            SixBtn.setBackgroundResource(R.drawable.btn);
            ThreeBtn.setBackgroundResource(R.drawable.btn);
            TwoBtn.setBackgroundResource(R.drawable.btn);
            OneBtn.setBackgroundResource(R.drawable.btn);
            FourBtn.setBackgroundResource(R.drawable.btn);
            MultiplyBtn.setBackgroundResource(R.drawable.mul_btn_d);
            NineBtn.setBackgroundResource(R.drawable.btn);
            EightBtn.setBackgroundResource(R.drawable.btn);
            SevenBtn.setBackgroundResource(R.drawable.btn);
            DividBtn.setBackgroundResource(R.drawable.div_btn_d);
            AcBtn.setBackgroundResource(R.drawable.ac_btn_d);
            RemoveBtn.setBackgroundResource(R.drawable.rem_btn_d);
            ModelusBtn.setBackgroundResource(R.drawable.med_btn_d);
            mainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBackgroundDark));
            ResultText.setTextColor((ContextCompat.getColor(this, R.color.ResultDark)));
            toggleDarkModeButton.setBackgroundResource(R.drawable.light_mode_24);
            OneBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            TwoBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            ThreeBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            FourBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            FiveBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            SixBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            SevenBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            EightBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            NineBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            DzeroBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
            ZeroBtn.setTextColor((ContextCompat.getColor(this, R.color.Text_d)));
        }
        isDarkMode = !isDarkMode;

    }
}
