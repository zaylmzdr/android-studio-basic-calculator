package com.example.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected Button button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonzero, button11;
    protected Button buttonac, buttonisaret, buttonyuzde;
    protected Button buttondiv, buttonmulti, buttonincrease, buttondecrease, buttonequal;
    protected TextView sonuc, cozum;

    protected double first;
    protected double second;
    protected double result;
    protected String operation;
    protected String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setClickListeners();
    }

    private void initializeViews() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonzero = findViewById(R.id.buttonzero);
        button11 = findViewById(R.id.button11);

        buttonac = findViewById(R.id.buttonac);
        buttonisaret = findViewById(R.id.buttonisaret);
        buttonyuzde = findViewById(R.id.buttonyuzde);

        buttondiv = findViewById(R.id.buttondiv);
        buttonmulti = findViewById(R.id.buttonmulti);
        buttonincrease = findViewById(R.id.buttonincrease);
        buttondecrease = findViewById(R.id.buttondecrease);
        buttonequal = findViewById(R.id.buttonequal);

        sonuc = findViewById(R.id.sonuc);
        cozum = findViewById(R.id.cozum);
    }

    private void setClickListeners() {
        // Sayı butonları için click listener'lar atanması
        button1.setOnClickListener(v -> handleNumberButtonClick(button1.getText().toString()));
        button2.setOnClickListener(v -> handleNumberButtonClick(button2.getText().toString()));
        button3.setOnClickListener(v -> handleNumberButtonClick(button3.getText().toString()));
        button4.setOnClickListener(v -> handleNumberButtonClick(button4.getText().toString()));
        button5.setOnClickListener(v -> handleNumberButtonClick(button5.getText().toString()));
        button6.setOnClickListener(v -> handleNumberButtonClick(button6.getText().toString()));
        button7.setOnClickListener(v -> handleNumberButtonClick(button7.getText().toString()));
        button8.setOnClickListener(v -> handleNumberButtonClick(button8.getText().toString()));
        button9.setOnClickListener(v -> handleNumberButtonClick(button9.getText().toString()));
        buttonzero.setOnClickListener(v -> handleNumberButtonClick(buttonzero.getText().toString()));
        button11.setOnClickListener(v -> handleNumberButtonClick(button11.getText().toString()));

        // Operatör butonları için click listener'lar atanması
        buttondiv.setOnClickListener(v -> handleOperatorButtonClick("÷"));
        buttonmulti.setOnClickListener(v -> handleOperatorButtonClick("×"));
        buttonincrease.setOnClickListener(v -> handleOperatorButtonClick("-"));
        buttondecrease.setOnClickListener(v -> handleOperatorButtonClick("+"));
        buttonyuzde.setOnClickListener(v -> handleOperatorButtonClick("%"));



        // Diğer butonlar için click listener'lar
        buttonac.setOnClickListener(v -> {
            sonuc.setText(null);
            cozum.setText(null);
        });

        buttonisaret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String backspace=null;
                if(sonuc.getText().length()>0){
                    StringBuilder stringBuilder = new StringBuilder(sonuc.getText());
                    stringBuilder.deleteCharAt(sonuc.getText().length()-1);
                    backspace=stringBuilder.toString();
                    sonuc.setText(backspace);

                }


            }
        });

        // Eşittir butonu için click listener atanması
        buttonequal.setOnClickListener(v -> handleEqualsButtonClick());
    }

    private void handleNumberButtonClick(String number) {
        String currentText = sonuc.getText().toString();
        // Eğer şu anda metin boşsa veya sadece '0' ise ve kullanıcı bir sayı girerse, sıfırı sil
        if ((TextUtils.isEmpty(currentText) || currentText.equals("0")) && !number.equals(".")) {
            // Sıfırı silip yeni sayıyı yaz
            sonuc.setText(number);
        } else if (number.equals(".")) {
            // Eğer '.' girilirse ve metin içinde '.' yoksa, sıfırı otomatik olarak ekle
            if (!currentText.contains(".")) {
                sonuc.setText(currentText + number);
            }
        } else {
            // Diğer durumlarda, girilen sayıyı metne ekle
            sonuc.setText(currentText + number);
        }
    }



    private void handleOperatorButtonClick(String op) {
        if (!TextUtils.isEmpty(sonuc.getText())) {
            first = Double.parseDouble(sonuc.getText().toString());
            operation = op;
            cozum.setText(String.valueOf(first));
            sonuc.setText("");
        }
    }



    private void handleEqualsButtonClick() {
        if (!TextUtils.isEmpty(sonuc.getText())) {
            second = Double.parseDouble(sonuc.getText().toString());
            if (operation != null) {
                switch (operation) {
                    case "+":
                        result = first + second;
                        break;
                    case "-":
                        result = first - second;
                        break;
                    case "÷":
                        if (second != 0) {
                            result = first / second;
                        } else {
                            String errorMessage = "Hata!";
                            sonuc.setText(errorMessage);
                            return;
                        }
                        break;
                    case "×":
                        result = first * second;
                        break;
                    case "%":
                        result = (first * second) / 100;
                        break;
                }
                answer = String.format("%.2f", result);
                sonuc.setText(answer);
                cozum.setText("");
            }
        }
    }
}
