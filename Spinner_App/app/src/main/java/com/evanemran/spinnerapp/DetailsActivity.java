package com.evanemran.spinnerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    ImageView imageView_details;
    TextView textView_menu_title;
    TextView textView_menu_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView_details = findViewById(R.id.imageView_details);
        textView_menu_title = findViewById(R.id.textView_menu_title);
        textView_menu_details = findViewById(R.id.textView_menu_details);

        Menu menu = (Menu) getIntent().getSerializableExtra("menu");

        Picasso.get().load(menu.image).into(imageView_details);
        textView_menu_title.setText(menu.name);
        textView_menu_details.setText(menu.details);
    }
}