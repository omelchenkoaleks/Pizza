package com.omelchenkoaleks.pizza;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // необходимо использовать этот метод из библиотеки поддержки
        ActionBar actionBar = getSupportActionBar();
        // включаем кнопку вверх
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
