package org.thewheatfield.android.deeplinks;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public String TAG = "DEEP_LINK_EXAMPLE_APP";

    TextView log = null;
    EditText location = null;
    Button goToLocation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        location = (EditText) findViewById(R.id.location);
        goToLocation = (Button) findViewById(R.id.goToLocation);
        log = (TextView) findViewById(R.id.log);

        if(goToLocation  != null) {
            goToLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = location.getText().toString();

                    log("Send intent, with data: " + data);
                    //Intent i = new Intent(Intent.ACTION_SENDTO);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(data));
                    try
                    {
                        startActivity(i);
                    }
                    catch(Exception e){
                        log(e.getMessage());
                    }

                }
            });
        }

        onNewIntent(getIntent());

    }
    public String format(int i) {
        return (i < 10 ? "0"+i : ""+i);
    }
    protected void log(String str){
        Log.i(TAG, str);
        Calendar c = Calendar.getInstance();

        String date = c.get(Calendar.YEAR) + "-" +
                format(c.get(Calendar.MONTH)) + "-" +
                format(c.get(Calendar.DAY_OF_MONTH) )+ " " +
                format(c.get(Calendar.HOUR_OF_DAY)) + ":" +
                format(c.get(Calendar.MINUTE))  + ":" +
                format(c.get(Calendar.SECOND));
        String data = date + ": " + str;
        log.setText(data + "\n" + log.getText());
    }

    protected void onNewIntent(Intent intent) {
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            log("Intent received, with data: " + data);
        }
    }


}
