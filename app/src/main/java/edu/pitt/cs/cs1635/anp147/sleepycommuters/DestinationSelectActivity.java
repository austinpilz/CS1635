package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.util.Log;

public class DestinationSelectActivity extends AppCompatActivity {

    public static final String TAG = DestinationSelectActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_select);

        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.destinationHeadText);
        textView.setText("Showing destination stops for the " + intent.getStringExtra("line") + " originating at "+  intent.getStringExtra("stopName"));

        LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.destinationLV);

        final RadioButton[] rb = new RadioButton[5];
        final RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        for (int i = 1; i < 5; i++) {
            rb[i] = new RadioButton(this);
            rg.addView(rb[i]);
            rb[i].setText("Destination Stop " + i);
        }
        mLinearLayout.addView(rg);


        Button button= (Button) findViewById(R.id.createOneTime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit = (EditText)findViewById(R.id.numStops);

                Intent myIntent = new Intent(DestinationSelectActivity.this, MapsActivity.class);
                myIntent.putExtra("oneTimeCreated", ""); //Optional parameters
                myIntent.putExtra("numberOfStops", mEdit.getText().toString()); //Optional parameters
                DestinationSelectActivity.this.startActivity(myIntent);
            }
        });

        Button buttonR = (Button) findViewById(R.id.createRecurring);
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit = (EditText)findViewById(R.id.numStops);
                int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));
                RadioButton r = (RadioButton)  rg.getChildAt(index);
                String destination = r.getText().toString();
                Log.d(TAG, destination);

                Intent myIntent = new Intent(DestinationSelectActivity.this, RecurringEditActivity.class);
                myIntent.putExtra("newAlert"," New Alert"); //Optional parameters
                myIntent.putExtra("line", intent.getStringExtra("line"));
                myIntent.putExtra("stopName", intent.getStringExtra("stopName"));
                myIntent.putExtra("destination", destination);

                myIntent.putExtra("numStops", mEdit.getText().toString());

                DestinationSelectActivity.this.startActivity(myIntent);
            }
        });


    }
}
