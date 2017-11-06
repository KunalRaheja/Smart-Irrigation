package com.example.kunal.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class charts extends AppCompatActivity {


    int moisture=1,humidity=2,temp=3;
    Spinner spinner;
    private int action=2;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fields);


        final WebView vistaWeb = (WebView) findViewById(R.id.temp);

        vistaWeb.clearCache(true);
        vistaWeb.clearHistory();
        vistaWeb.getSettings().setJavaScriptEnabled(true);
        vistaWeb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                action =pos+1;


                String summary = "<html><body><iframe width=\"100%\" height=\"100%\" style=\"border: 1px solid #cccccc;\" src=\"https://thingspeak.com/channels/276453/charts/"+action+"?bgcolor=%23ffffff&color=%23d62020&dynamic=true&results=30&type=line&update=10\"></iframe></body></html>";
                vistaWeb.loadData(summary, "text/html", null);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // OR, you can also load from an HTML string:


    }






}
