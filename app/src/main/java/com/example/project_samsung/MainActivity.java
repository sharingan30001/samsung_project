package com.example.project_samsung;

import static android.app.ProgressDialog.show;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project_samsung.RGB;

import java.lang.reflect.Type;
import java.util.Arrays;

import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText type = findViewById(R.id.type);
        EditText editText = findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        Button button1, button2;
        final RelativeLayout relativeLayout;
        // set button 1 with its id
        // set button 2 with its id
        // set relative layout with its id
        relativeLayout = findViewById(R.id.rlVar1);
        button.setOnClickListener(new View.OnClickListener() {
            private void RGB(String code) {
                String[] a = code.split(" ");
                int[] ints = new int[3];
                for (int i = 0; i < a.length; ++i)
                    ints[i] = Integer.parseInt(a[i]);
                relativeLayout.setBackgroundColor(Color.rgb(ints[0], ints[1], ints[2]));
                return;
            }
            private void HSV(String code) {
                String[] a = code.split(" ");
                float[] floats = new float[3];
                for (int i = 0; i < a.length; ++i)
                    floats[i] = Float.parseFloat(a[i]);
                relativeLayout.setBackgroundColor(Color.HSVToColor(floats));
                return;
            }

            private void CMYK(String code) {
                String[] a = code.split(" ");
                int[] ints = new int[4];
                for (int i = 0; i < a.length; ++i)
                    ints[i] = Integer.parseInt(a[i]);
                RGB rgb = new RGB();
                rgb = rgb.get_RGB_from_CMYK(ints[0], ints[1], ints[2], ints[3]);
                relativeLayout.setBackgroundColor(Color.rgb(rgb.R, rgb.G, rgb.B));
                return;
            }

            private void HEX(String code) {
                String[] f = new String[3];
                f[0] = code.substring(0, 2);
                f[1] = code.substring(2, 4);
                f[2] = code.substring(4, 6);
                RGB rgb = new RGB();
                rgb = rgb.get_RGB_from_HEX(f);
                relativeLayout.setBackgroundColor(Color.rgb(rgb.R, rgb.G, rgb.B));
            }

            private void HSL(String code) {
                String[] a = code.split(" ");
                double[] floats = new double[3];
                for (int i = 0; i < a.length; ++i)
                    floats[i] = Double.parseDouble(a[i]);
                RGB rgb = new RGB();
                rgb = rgb.get_RGB_from_HSL(floats[0], floats[1], floats[2]);
                //relativeLayout.setBackgroundColor(Color.rgb(rgb.R, rgb.G, rgb.B));
            }
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String text = type.getText().toString();
                if (text.equals("HSB"))
                    text = "HSV";
                if (text.equals("HLS") || text.equals("HSI"))
                    text = "HSL";
                String code = editText.getText().toString();
                code = code.replace("#", "");
                if (code.charAt(0) == ' ')
                    code = code.replace(" ", "");
                switch (text) {
                    case "RGB": {
                        RGB(code);
                        break;
                    }
                    case "HSV": {
                        HSV(code);
                        break;
                    }
                    case "CMYK": {
                        CMYK(code);
                        break;
                    }
                    case "HSL": {
                        HSL(code);
                        break;
                    }
                    default: {
                        code = code.replace("#", "");
                        code = code.replace(" ", "");
                        HEX(code);
                        break;
                    }
                }
            }
        });
    }
}