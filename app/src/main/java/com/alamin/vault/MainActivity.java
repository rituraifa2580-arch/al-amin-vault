package com.alamin.vault;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;
import android.text.InputType;

public class MainActivity extends Activity {

    LinearLayout root;
    int bg = Color.rgb(12, 14, 23);
    int card = Color.rgb(25, 29, 43);
    int accent = Color.rgb(105, 95, 255);
    int white = Color.WHITE;
    int muted = Color.rgb(160, 165, 185);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLogin();
    }

    GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        return t;
    }

    void setup() {
        root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(22, 25, 22, 20);
        root.setBackgroundColor(bg);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(root);
        setContentView(scroll);
    }

    void showLogin() {
        setup();
        root.setGravity(Gravity.CENTER);

        TextView logo = text("🔐", 65, white);
        logo.setGravity(Gravity.CENTER);
        root.addView(logo);

        TextView title = text("AL-AMIN VAULT", 27, white);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title);

        TextView sub = text("YOUR PRIVATE DIGITAL SPACE", 12, muted);
        sub.setGravity(Gravity.CENTER);
        root.addView(sub);

        EditText pin = new EditText(this);
        pin.setHint("Enter PIN");
        pin.setTextColor(white);
        pin.setHintTextColor(muted);
        pin.setInputType(InputType.TYPE_CLASS_NUMBER |
                InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        pin.setGravity(Gravity.CENTER);
        pin.setBackground(shape(card, 20));
        pin.setPadding(20, 15, 20, 15);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 35, 0, 15);
        root.addView(pin, p);

        Button login = new Button(this);
        login.setText("UNLOCK VAULT");
        login.setTextColor(white);
        login.setBackground(shape(accent, 20));
        root.addView(login, p);

        login.setOnClickListener(v -> {
            if (pin.getText().toString().equals("2580")) {
                showDashboard();
            } else {
                Toast.makeText(this, "Wrong PIN",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    void addCard(String emoji, String name, String description,
                 Runnable action) {

        LinearLayout box = new LinearLayout(this);
        box.setOrientation(1);
        box.setPadding(20, 17, 20, 17);
        box.setBackground(shape(card, 20));

        TextView icon = text(emoji, 30, white);
        box.addView(icon);

        TextView title = text(name, 19, white);
        title.setTypeface(null, Typeface.BOLD);
        box.addView(title);

        TextView sub = text(description, 12, muted);
        box.addView(sub);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 8, 0, 8);

        root.addView(box, p);
        box.setOnClickListener(v -> action.run());
    }

    void showDashboard() {
        setup();

        TextView brand = text("AL-AMIN VAULT", 25, white);
        brand.setTypeface(null, Typeface.BOLD);
        root.addView(brand);

        TextView welcome = text("Welcome to your private space", 14, muted);
        root.addView(welcome);

        TextView heading = text("YOUR VAULT", 17, white);
        heading.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams hp =
                new LinearLayout.LayoutParams(-1, -2);
        hp.setMargins(0, 28, 0, 10);
        root.addView(heading, hp);

        addCard("🔑", "Password Manager",
                "Manage your saved passwords",
                () -> showSection("Password Manager"));

        addCard("📝", "Private Notes",
                "Keep your personal notes",
                () -> showSection("Private Notes"));

        addCard("🖼️", "Photo & Video Vault",
                "Your private media space",
                () -> showSection("Photo & Video Vault"));

        addCard("📁", "File Vault",
                "Keep your important files",
                () -> showSection("File Vault"));

        addCard("⚙️", "Settings",
                "App preferences and security",
                () -> showSection("Settings"));

        Button lock = new Button(this);
        lock.setText("LOCK VAULT");
        lock.setTextColor(white);
        lock.setBackground(shape(accent, 18));
        root.addView(lock);

        lock.setOnClickListener(v -> showLogin());
    }

    void showSection(String name) {
        setup();

        TextView title = text(name, 25, white);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        TextView info = text(
                "This section is ready for the next development step.",
                14, muted);

        LinearLayout.LayoutParams p =
                new LinearLayout.LayoutParams(-1, -2);
        p.setMargins(0, 20, 0, 20);
        root.addView(info, p);

        if (name.equals("Settings")) {
            TextView security = text(
                    "Security settings\n\nPIN: Currently 2580\n\n"
                    + "Fingerprint: Not implemented yet\n\n"
                    + "Backup: Not implemented yet",
                    16, white);
            root.addView(security);
        }

        Button back = new Button(this);
        back.setText("BACK TO HOME");
        back.setTextColor(white);
        back.setBackground(shape(accent, 18));
        root.addView(back);
        back.setOnClickListener(v -> showDashboard());
    }
}    }
}
