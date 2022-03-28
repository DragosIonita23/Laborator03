package ro.pub.cs.systems.eim.lab03.phonedialer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

// IONITA DRAGOS 341 C1 LABORATOR 03 EIM - Interfata Grafica

public class MainActivity extends AppCompatActivity {
    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText phoneNumberText = (EditText)findViewById(R.id.phoneNumberEditText);

            String txt = phoneNumberText.getText().toString();

            if (view.getId() == R.id.deleteCharacterButton &&
                    txt != null && !txt.isEmpty()) {
                phoneNumberText.setText(txt.substring(0, txt.length() - 1));
            } else if (view.getId() == R.id.callButton) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            12345); // my request code
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNumberText.getText().toString()));
                    startActivity(intent);
                }
            } else if (view.getId() == R.id.finishButton) {
                finish();
            } else if (view.getId() == R.id.contactsManagerButton) {
                if (txt.length() > 0) {
                    Intent intent = new Intent("ro.pub.cs.systems.eim.lab04.contactsmanager.intent.action.ContactsManagerActivity");
                    intent.putExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY", txt);
                    startActivityForResult(intent, 1234567);
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
                }
            } else if (view.getId() != R.id.finishButton &&
                    view.getId() != R.id.callButton &&
                    view.getId() != R.id.deleteCharacterButton) {
                if (txt == null || txt.isEmpty()) {
                    phoneNumberText.setText(String.format("%s", ((AppCompatButton)view).getText().toString()));
                } else {
                    phoneNumberText.setText(String.format("%s", txt + ((AppCompatButton)view).getText().toString()));
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        EditText phoneNumberText = (EditText)findViewById(R.id.phoneNumberEditText);
        phoneNumberText.setEnabled(false);

        ButtonListener btnListener = new ButtonListener();
        Button button_0 = findViewById(R.id.button_0);
        button_0.setOnClickListener(btnListener);
        Button button_1 = findViewById(R.id.button_1);
        button_1.setOnClickListener(btnListener);
        Button button_2 = findViewById(R.id.button_2);
        button_2.setOnClickListener(btnListener);
        Button button_3 = findViewById(R.id.button_3);
        button_3.setOnClickListener(btnListener);
        Button button_4 = findViewById(R.id.button_4);
        button_4.setOnClickListener(btnListener);
        Button button_5 = findViewById(R.id.button_5);
        button_5.setOnClickListener(btnListener);
        Button button_6 = findViewById(R.id.button_6);
        button_6.setOnClickListener(btnListener);
        Button button_7 = findViewById(R.id.button_7);
        button_7.setOnClickListener(btnListener);
        Button button_8 = findViewById(R.id.button_8);
        button_8.setOnClickListener(btnListener);
        Button button_9 = findViewById(R.id.button_9);
        button_9.setOnClickListener(btnListener);
        Button button_star = findViewById(R.id.button_star);
        button_star.setOnClickListener(btnListener);
        Button button_hashtag = findViewById(R.id.button_hashtag);
        button_hashtag.setOnClickListener(btnListener);
        ImageButton button_call = findViewById(R.id.callButton);
        button_call.setOnClickListener(btnListener);
        ImageButton button_finish = findViewById(R.id.finishButton);
        button_finish.setOnClickListener(btnListener);
        ImageButton button_delete_char = findViewById(R.id.deleteCharacterButton);
        button_delete_char.setOnClickListener(btnListener);
        ImageButton constacts_manager_button = findViewById(R.id.contactsManagerButton);
        constacts_manager_button.setOnClickListener(btnListener);
    }
}