package com.example.webapitest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class WebAPITest extends Activity {

    // Manipulate query string as needed
    // This example only displays one row

    private String URL = "http://frigg.hiof.no/bo14-g23/hcserv.py?q=conversation&user_id_sender=3&user_id_receiver=4";
    private TextView senderID, receiverID, message, sent, read;
    private Button loadBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        loadBtn = (Button)findViewById(R.id.loadBtn);
        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAsyncTask();
            }
        });

        senderID = (TextView) findViewById(R.id.sid);
        receiverID = (TextView) findViewById(R.id.rid);
        message = (TextView) findViewById(R.id.message);
        sent = (TextView) findViewById(R.id.sent);
        read = (TextView) findViewById(R.id.read);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startAsyncTask() {
        new asyncJSONTask().execute();
    }

    private class asyncJSONTask extends AsyncTask<String, Void, String> {

        JSONObject jsonObject;

        protected String doInBackground(String... strings) {
            try {
                // Creating JSON object with string from the HTTP GET request
                jsonObject = new JSONObject(HTTPClient.getJSONString(URL));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                // Assigning values from JSON object
                senderID.setText(jsonObject.getString("user_id_sender"));
                receiverID.setText(jsonObject.getString("user_id_receiver"));
                message.setText(jsonObject.getString("message"));
                sent.setText(jsonObject.getString("sent"));
                read.setText(jsonObject.getString("read"));
            }
            catch (NullPointerException e){
                e.printStackTrace();
                loadBtn.setText("Check SOUT");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
