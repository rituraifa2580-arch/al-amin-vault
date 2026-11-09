package com.alamin.vault;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {

    EditText pinInput;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(25, 25, 25, 25);
        layout.setBackgroundColor(Color.rgb(20, 20, 25));

        TextView title = new TextView(this);
        title.setText("CALCULATOR");
        title.setTextColor(Color.WHITE);
        title.setTextSize(27);
        title.setGravity(Gravity.CENTER);
        layout.addView(title);

        pinInput = new EditText(this);
        pinInput.setHint("Enter PIN");
        pinInput.setTextColor(Color.WHITE);
        pinInput.setHintTextColor(Color.GRAY);
        pinInput.setInputType(2);
        pinInput.setGravity(Gravity.CENTER);
        layout.addView(pinInput);

        String[] keys = {
            "1", "2", "3",
            "4", "5", "6",
            "7", "8", "9",
            "C", "0", "="
        };

        for (String key : keys) {
            Button button = new Button(this);
            button.setText(key);

            button.setOnClickListener(v -> {
                if (key.equals("C")) {
                    pinInput.setText("");
                } else if (key.equals("=")) {
                    if (pinInput.getText().toString().equals("2580")) {
                        openVault();
                    } else {
                        Toast.makeText(this,
                            "Wrong PIN", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    pinInput.append(key);
                }
            });

            layout.addView(button);
        }

        setContentView(layout);
    }

    private void openVault() {
        layout.removeAllViews();

        TextView vault = new TextView(this);
        vault.setText("🔐 AL-AMIN PRIVATE VAULT\n\nWelcome!\n\nYour private space is ready.");
        vault.setTextColor(Color.WHITE);
        vault.setTextSize(23);
        vault.setGravity(Gravity.CENTER);

        layout.addView(vault);
    }
}
