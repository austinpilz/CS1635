package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.util.Log;

import java.util.ArrayList;

public class DestinationSelectActivity extends AppCompatActivity {

    public static final String TAG = DestinationSelectActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_select);

        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.destinationHeadText);
        textView.setText("Showing destination stops for the " + intent.getStringExtra("line") + " originating at "+  intent.getStringExtra("stopName"));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        LinearLayout mLinearLayout = (LinearLayout) findViewById(R.id.destinationLV);

        final RadioButton[] rb = new RadioButton[11];
        final RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        ArrayList<String> stops = new ArrayList();
        stops.add("East Liberty Station");
        stops.add("Liberty Ave at 10th St");
        stops.add("Smithfield St at Sixth Ave");
        stops.add("Penn Station");
        stops.add("Hay St Ramp");
        stops.add("Fifth Ave at N Craig St");
        stops.add("Fifth Ave at Robinson St");
        stops.add("Fifth Ave at Oakland Ave");
        stops.add("Fifth Ave at Craft Ave");


        for (int i = 0; i < stops.size(); i++) {
            rb[i] = new RadioButton(this);
            rg.addView(rb[i]);
            rb[i].setText(stops.get(i));
        }
        mLinearLayout.addView(rg);


        Button button= (Button) findViewById(R.id.createOneTime);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mEdit = (EditText)findViewById(R.id.numStops);
                mEdit.setSelection(mEdit.getText().length());

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
                mEdit.setSelection(mEdit.getText().length());
                int index = rg.indexOfChild(findViewById(rg.getCheckedRadioButtonId()));
                RadioButton r = (RadioButton)  rg.getChildAt(index);
                String destination;
                if(index != -1) {
                    destination = r.getText().toString();
                }
                else {
                    destination = "Alumni Hall";
                }


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
