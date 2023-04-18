package com.project.morselite;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AsciToMorse extends Fragment {
    Flash flash;
    private EditText mTextBefore;
    private TextView mMorseText;
    Map<Character, String> morseMap = new HashMap<>();
    private CameraManager cameraManager;
    private String getCameraID;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.text_to_morse, container, false);

        mTextBefore = parentView.findViewById(R.id.textInput);
        mMorseText = parentView.findViewById(R.id.morseOutput);
        mTextBefore.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    closeKeyboard(v);
                }
            }
        });


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
        morseMap.put(' ', "/");

        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);
        try {
            // O means back camera unit,
            // 1 means front camera unit
            getCameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }



//        Button bstop = (Button) parentView.findViewById(R.id.stop);
//        bstop.setOnClickListener(this::stop);


        Button bconvert = (Button) parentView.findViewById(R.id.convert);
        bconvert.setOnClickListener(this::convert);

        Button bflash = (Button) parentView.findViewById(R.id.flash);
        bflash.setOnClickListener(view -> {
            try {
                emitMorse(view);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        return parentView;
    }

//        public void newThread(){
//            thread = new Thread(flash);
//        }

    public void convert(View view){
        String plainText = mTextBefore.getText().toString();
        plainText = plainText.toLowerCase();
        String morseText = "";

        for(int i = 0; i < plainText.length(); i++){
            morseText += morseMap.get(plainText.charAt(i));
            morseText += " ";
        }

        closeKeyboard(view);

        mMorseText.setText(morseText);
    }


//    public void stop(View view) {
//        thread.interrupt();
//        thread = new Thread(flash);
//    }

    public void setText(View view){
        String newText = mTextBefore.getText().toString();
        mTextBefore.setText(newText);
        closeKeyboard(view);
    }

    private void closeKeyboard(View view){
        try{
            if (view != null){
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch(Exception e){
            //handles it
        }
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
    public void emitMorse(View view) throws InterruptedException{
        flash = new Flash(cameraManager, getCameraID);
        Thread thread = new Thread(flash);
        String morseToSend = getMorse();
        flash.updateMorse(morseToSend);
        thread.start();

    }

}