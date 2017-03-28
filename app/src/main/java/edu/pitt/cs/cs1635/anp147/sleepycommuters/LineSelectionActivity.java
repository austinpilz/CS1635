package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class LineSelectionActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_selection);


        Intent intent = getIntent();

        setContentView(R.layout.activity_line_selection);
        TextView textView = (TextView) findViewById(R.id.lineSelectionStopName);
        textView.setText("Showing lines for stop " + intent.getStringExtra("stopName"));
    }

}
