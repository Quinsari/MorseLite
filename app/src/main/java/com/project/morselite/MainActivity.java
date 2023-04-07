package com.project.morselite;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Flash flash = new Flash();
    private EditText mTextBefore;
    private TextView mMorseText;
    Map<Character, String> morseMap = new HashMap<>();
    private CameraManager cameraManager;
    private String getCameraID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextBefore = findViewById(R.id.textInput);
        mMorseText = findViewById(R.id.morseOutput);

        morseMap.put('a', ".-");
        morseMap.put('b', "-...");
        morseMap.put('c', "-.-.");
        morseMap.put('d', "-..");
        morseMap.put('e', ".");
        morseMap.put('f', "..-.");
        morseMap.put('g', "--.");
        morseMap.put('h', "....");
        morseMap.put('i', "..");
        morseMap.put('j', ".---");
        morseMap.put('k', "-.-");
        morseMap.put('l', ".-..");
        morseMap.put('m', "--");
        morseMap.put('n', "-.");
        morseMap.put('o', "---");
        morseMap.put('p', ".--.");
        morseMap.put('q', "--.-");
        morseMap.put('r', ".-.");
        morseMap.put('s', "...");
        morseMap.put('t', "-");
        morseMap.put('u', "..-");
        morseMap.put('v', "...-");
        morseMap.put('w', ".--");
        morseMap.put('x', "-..-");
        morseMap.put('y', "-.--");
        morseMap.put('z', "--..");
        morseMap.put('1', ".----");
        morseMap.put('2', "..---");
        morseMap.put('3', "...--");
        morseMap.put('4', "....-");
        morseMap.put('5', ".....");
        morseMap.put('6', "-....");
        morseMap.put('7', "--...");
        morseMap.put('8', "---..");
        morseMap.put('9', "----.");
        morseMap.put('0', "-----");

        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void convert(View view){
        String plainText = mTextBefore.getText().toString();
        plainText = plainText.toLowerCase();
        String morseText = "";

        for(int i = 0; i < plainText.length(); i++){
            morseText += morseMap.get(plainText.charAt(i));
            morseText += " ";
        }

        mMorseText.setText(morseText);
    }

    public String getMorse(){
        String plainText = mTextBefore.getText().toString();
        String morseText = "";

        for(int i = 0; i < plainText.length(); i++){
            morseText += morseMap.get(plainText.charAt(i));
            morseText += " ";
        }
        return morseText;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void emitMorse(View view){
        String morseToSend = getMorse();
        flash.emitFlash(morseToSend, cameraManager, getCameraID);
    }

}