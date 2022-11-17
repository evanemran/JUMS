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

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LinearLayout tel_container, mail_container, web_container, helpline_container;
    TextView address_container, sq_website_container;

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
        address_container = findViewById(R.id.address_container);
        address_container.setOnClickListener(clickListener);
        sq_website_container = findViewById(R.id.sq_website_container);
        sq_website_container.setOnClickListener(clickListener);


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
                case R.id.web_container:
                    openUrl("https://squareit.com.bd/");
                    break;
                case R.id.address_container:
                    openLocation(23.777654383336465, 90.40564976441003);
                    break;
                case R.id.sq_website_container:
                    openUrl("https://squaregroup.com/");
                    break;
                case R.id.helpline_container:
                    showTelDialog("09613707070");
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

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},100);
        }
        else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+ number));
            startActivity(callIntent);
        }
    }

    private void sms(String number) {

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},101);
        }
        else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null)));
        }
    }

    private void email(String address) {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{ address});
        email.putExtra(Intent.EXTRA_SUBJECT, "Regarding Visiting Card");
        email.putExtra(Intent.EXTRA_TEXT, "Hello");

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }

    private void openUrl(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    private void openLocation(double latitude, double longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }
}