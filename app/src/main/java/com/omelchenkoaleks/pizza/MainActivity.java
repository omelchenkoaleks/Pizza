package com.omelchenkoaleks.pizza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // получаем ссылку на панель инструментов
        Toolbar toolbar = findViewById(R.id.toolbar);
        // назначаем панель инструментов панелью действий активности
        setSupportActionBar(toolbar);
    }
}
