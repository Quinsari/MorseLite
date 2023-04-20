package com.project.morselite;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Dictionary extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myColorTint();
        return inflater.inflate(R.layout.dictionary, container, false);
    }

    private void myColorTint() {
        int tint;
        int drawables[] = { R.drawable.international_morse_code };
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;;
        switch (getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) {
            case Configuration.UI_MODE_NIGHT_YES:
                tint = Color.parseColor("#FFFFFF"); // R.color.blue;
                // add your drawable resources you wish to tint to the drawables array...
                for (int id : drawables) {
                    Drawable icon = getResources().getDrawable(id);
                    icon.setColorFilter(tint,mode);
                }
                break;
            case Configuration.UI_MODE_NIGHT_NO:
                tint = Color.parseColor("#000000"); // R.color.blue;
                // add your drawable resources you wish to tint to the drawables array...
                for (int id : drawables) {
                    Drawable icon = getResources().getDrawable(id);
                    icon.setColorFilter(tint,mode);
                }
                break;
            default:
                break;
        }

    }

}
