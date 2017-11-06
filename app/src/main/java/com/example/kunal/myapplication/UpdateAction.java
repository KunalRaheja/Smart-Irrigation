package com.example.kunal.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.AccessController;

public class UpdateAction extends Activity {

    int action=0;
    TextView motor;
    TextView moist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_action);


        Button on=findViewById(R.id.button_on);
        Button off=findViewById(R.id.button_off);
        Button up=findViewById(R.id.button_update);
        motor=findViewById(R.id.status);
        moist=findViewById(R.id.moisture);

        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action=1;
                motor.setText("Motor: ON");
            }
        });

        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                action=0;
                motor.setText("Motor: OFF");

            }
        });


        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new FetchThingspeakTask().execute();
            }
        });

    }




    class FetchThingspeakTask extends AsyncTask<Void, Void, String> {

        //Location target;

        protected void onPreExecute() {
            // progress bar
            motor.setText("Done..............");
        }

        protected String doInBackground(Void... urls) {
            try {
                URL url = new URL("https://api.thingspeak.com/channels/276453.json");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {




            if(response == null) {
                Toast.makeText(UpdateAction.this, "There was an error", Toast.LENGTH_SHORT).show();
                motor.setText("Error");
                return;
            }
            //  latitudeEdit.setEnabled(true);
            // longitudeEdit.setEnabled(true);

            // distanceText.setVisibility(View.VISIBLE);
            //  progressBar.setVisibility(View.GONE);

            try {
                JSONObject channel = (JSONObject) new JSONTokener(response).nextValue();
                motor.setText(response);
              //  double moisture = channel.getDouble("moisture");
                //  double longitude = channel.getDouble(THINGSPEAK_FIELD2);

                // float distance = location.distanceTo(target);
               // motor.setText("Done");
                // Log.e(TAG, "distance == " + distance);
            } catch (JSONException e) {
                motor.setText("Error"+e);
                e.printStackTrace();
            }
        }
    }







}

class UpdateThingspeakTask extends AsyncTask<Integer, Void, String> {

    private Exception exception;
    int action;
    @Override
    protected String doInBackground(Integer... integers) {
        action=integers[0];
        return null;
    }

    protected void onPreExecute() {
      //******************  Toast.makeText(getApplicationContext(), "Updating !!", Toast.LENGTH_SHORT).show();
    }

    protected String doInBackground(Void... urls) {
        try {
            URL url = new URL("https://api.thingspeak.com/update?api_key=YQIPJGVKMJMAOEX3&field4="+action);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        // We completely ignore the response
        // Ideally we should confirm that our update was successful
       //********** Toast.makeText(UpdateAction.this,"Action Updated!!",Toast.LENGTH_SHORT).show();
    }

}









