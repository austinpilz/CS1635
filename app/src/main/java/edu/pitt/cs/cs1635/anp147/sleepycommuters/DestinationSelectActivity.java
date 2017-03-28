package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class DestinationSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_select);

        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.destinationHeadText);
        textView.setText("Showing destination stops for the " + intent.getStringExtra("line") + " originating at "+  intent.getStringExtra("stopName"));

        LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.destinationLV);

        final RadioButton[] rb = new RadioButton[5];
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int i = 1; i < 5; i++) {
            rb[i] = new RadioButton(this);
            rg.addView(rb[i]);
            rb[i].setText("Destination Stop " + i);

        }
        mLinearLayout.addView(rg);


    }
}
