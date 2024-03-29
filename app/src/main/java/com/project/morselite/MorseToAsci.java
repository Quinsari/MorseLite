package com.project.morselite;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

public class MorseToAsci extends Fragment {

    private StringBuilder inputBuilder;         // StringBuilder used to have a dynamic object for output
    private TextView inputView;                 // inputView is the lower text box, used for input but is NOT a textInput
    private TextView outputView;                // outputView is where the translated morse is displayed
    private Map<String, Character> alphaMap;    // alphaMap is a hashmap used as the dictionary for translation
    private SharedPreferences sharedPref;       // sharedPref and editor are used to store/retrieve the morse string from the input field
    private SharedPreferences.Editor editor;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setRetainInstance(true);

        View parentView = inflater.inflate(R.layout.morse_to_text, container, false);

        // instantiate / assign variables
        inputView = parentView.findViewById(R.id.morseInput);
        outputView = parentView.findViewById(R.id.alphaOutput);
        inputBuilder = new StringBuilder();
        alphaMap = new HashMap<>();
        sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        // Fill the dictionary
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

        // Click Listeners for all 5 buttons
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

        // Attempt to pull a String using the key "morseIn", if it's null, use "" (empty String)
        inputBuilder.append(sharedPref.getString("morseIn", ""));

        showMorse();

        return parentView;
    }

    // the three 'add' methods are listeners for the input buttons
    public void addDash(View view) {
        inputBuilder.append('-');
        showMorse();
    }

    public void addDot(View view) {
        inputBuilder.append('.');
        showMorse();
    }

    public void addSlash(View view) {
        String s = inputBuilder.toString();
        if (s.length() > 0 && s.charAt(s.length() - 1) != '/') {
            inputBuilder.append('/');
        }
        showMorse();
    }

    // showMorse is the main component used to update the TextViews
    private void showMorse() {
        String s = inputBuilder.toString();
        editor.putString("morseIn", s);
        editor.apply();
        inputView.setText(s);
        outputView.setText(morseToAlpha(s));
    }

    // takes a string of morse words separated by '/' and
    // returns the alphanumeric string produced via translation
    private String morseToAlpha(String s) {
        StringBuilder outputBuilder = new StringBuilder();
        if(s.contains("/")) {
            String[] words = s.split("/");
            for (String word : words) {
                if (alphaMap.get(word) != null) {
                    char c = alphaMap.get(word);
                    outputBuilder.append(c);
                }
            }
        } else if (s.length() > 0 && alphaMap.get(s) != null) {
            char c = alphaMap.get(s);
            outputBuilder.append(c);
        }
        return outputBuilder.toString();
    }

    public void clearMorse(View view) {
        inputBuilder = new StringBuilder();
        showMorse();
    }

    public void backWord(View view) {
        String temp = inputBuilder.toString();
        // If there are more than 1 /'s
        if (temp.indexOf('/') != temp.lastIndexOf('/')) {
            // remove to the previous /
            if (temp.lastIndexOf("/") == temp.length() - 1) {
                temp = temp.substring(0, temp.length() - 1);
            }
            temp = temp.substring(0, temp.lastIndexOf("/"));
            inputBuilder = new StringBuilder();
            inputBuilder.append(temp + "/");
            showMorse();
        }
        // If there is only 1 / && there is text after the /
        else if (temp.lastIndexOf('/') != -1 && temp.lastIndexOf('/') != temp.length() - 1) {
            // remove to the single /
            temp = temp.substring(0, temp.lastIndexOf("/"));
            inputBuilder = new StringBuilder();
            inputBuilder.append(temp + "/");
            showMorse();
        }
        else {
            clearMorse(view);
        }
    }
}
