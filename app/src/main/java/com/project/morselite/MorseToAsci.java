package com.project.morselite;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MorseToAsci extends AppCompatActivity {

    private StringBuilder inputBuilder;
    private TextView inputView;
    private TextView outputView;
    private Map<String, Character> alphaMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputView = findViewById(R.id.morseInput);
        outputView = findViewById(R.id.alphaOutput);

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
        outputView.setText(morseToAlpha(inputBuilder.toString()));
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
