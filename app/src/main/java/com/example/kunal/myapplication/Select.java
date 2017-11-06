package com.example.kunal.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Select extends AppCompatActivity {




    Button view,ctrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select2);

        view = (Button)findViewById(R.id.bt_view);
        ctrl= (Button)findViewById(R.id.bt_ctrl);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Select.this, charts.class);
                startActivity(i);
            }
        });
        ctrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Select.this, UpdateAction.class);
                startActivity(i);
            }
        });



    }

}
