package com.evanemran.visitingcard;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout tel_container, mail_container, web_container, helpline_container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tel_container = findViewById(R.id.tel_container);
        tel_container.setOnClickListener(clickListener);
        mail_container = findViewById(R.id.mail_container);
        mail_container.setOnClickListener(clickListener);
        web_container = findViewById(R.id.web_container);
        web_container.setOnClickListener(clickListener);
        helpline_container = findViewById(R.id.helpline_container);
        helpline_container.setOnClickListener(clickListener);


    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tel_container:
                    showTelDialog("01521329977");
                    break;
                case R.id.mail_container:
                    email("evan.sil@squaregroup.com");
                    break;
            }
        }
    };

    private void showTelDialog(String number) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_tel);
        dialog.setTitle("Select an action");
        Button callButton= (Button) dialog.findViewById(R.id.button_call);
        Button smsButton= (Button) dialog.findViewById(R.id.button_sms);
        TextView textView_header= (TextView) dialog.findViewById(R.id.textView_header);

        textView_header.setText("Select an action for " + number);

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(number);
                dialog.dismiss();
            }
        });

        smsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sms(number);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void call(String number) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},100);
        }
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+ number));
            startActivity(callIntent);
        }
    }

    private void sms(String number) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE},100);
        }
        else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
        }
    }

    private void email(String address) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}