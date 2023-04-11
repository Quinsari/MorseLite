package com.project.morselite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class MorseToAsci extends Fragment {

    private StringBuilder inputBuilder = new StringBuilder();
    private TextView inputView;
    private TextView outputView;
    private Map<String, Character> alphaMap = new HashMap<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.morse_to_text, container, false);


        inputView = parentView.findViewById(R.id.morseInput);
        outputView = parentView.findViewById(R.id.alphaOutput);



        alphaMap.put(".-", 'a');
        alphaMap.put("-...", 'b');
        alphaMap.put("-.-.", 'c');
        alphaMap.put("-..", 'd');
        alphaMap.put(".", 'e');
        alphaMap.put("..-.", 'f');
        alphaMap.put("--.", 'g');
        alphaMap.put("....", 'h');
        alphaMap.put("..", 'i');
        alphaMap.put(".---", 'j');
        alphaMap.put("-.-", 'k');
        alphaMap.put(".-..", 'l');
        alphaMap.put("--", 'm');
        alphaMap.put("-.", 'n');
        alphaMap.put("---", 'o');
        alphaMap.put(".--.", 'p');
        alphaMap.put("--.-", 'q');
        alphaMap.put(".-.", 'r');
        alphaMap.put("...", 's');
        alphaMap.put("-", 't');
        alphaMap.put("..-", 'u');
        alphaMap.put("...-", 'v');
        alphaMap.put(".--", 'w');
        alphaMap.put("-..-", 'x');
        alphaMap.put("-.--", 'y');
        alphaMap.put("--..", 'z');
        alphaMap.put(".----", '1');
        alphaMap.put("..---", '2');
        alphaMap.put("...--", '3');
        alphaMap.put("....-", '4');
        alphaMap.put(".....", '5');
        alphaMap.put("-....", '6');
        alphaMap.put("--...", '7');
        alphaMap.put("---..", '8');
        alphaMap.put("----.", '9');
        alphaMap.put("-----", '0');

        Button bclear = (Button) parentView.findViewById(R.id.clearButton);
        bclear.setOnClickListener(this::clearMorse);

        Button bback = (Button) parentView.findViewById(R.id.backWordButton);
        bback.setOnClickListener(this::backWord);

        Button bdash = (Button) parentView.findViewById(R.id.dashButton);
        bdash.setOnClickListener(this::addDash);

        Button bdot = (Button) parentView.findViewById(R.id.dotButton);
        bdot.setOnClickListener(this::addDot);

        Button bslash = (Button) parentView.findViewById(R.id.spaceButton);
        bslash.setOnClickListener(this::addSlash);

        return parentView;
    }


    // the three 'add' methods are listeners for the input buttons
    public void addDash(View view) {
        insertMorse('-');
    }

    public void addDot(View view) {
        insertMorse('.');
    }

    public void addSlash(View view) {
        insertMorse('/');
    }

    // insertMorse is the main component used to update the TextViews
    private void insertMorse(char c) {
        inputBuilder.append(c);
        inputView.setText(inputBuilder.toString());
        try {
            outputView.setText(morseToAlpha(inputBuilder.toString()));
        } catch(NullPointerException e){
            inputBuilder = new StringBuilder();
            inputView.setText("");
            outputView.setText("");
        }
    }

    // takes a string of morse words separated by '/' and
    // returns the alphanumeric string produced via translation
    private String morseToAlpha(String s) {
        StringBuilder outputBuilder = new StringBuilder();
        String[] words = s.split("/");
        for (String word : words) {
            char c = alphaMap.get(word);
            outputBuilder.append(c);
        }
        return outputBuilder.toString();
    }

    public void clearMorse(View view) {
        inputBuilder = new StringBuilder();
        inputView.setText("");
        outputView.setText("");
    }

    public void backWord(View view) {

    }
}
