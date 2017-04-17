package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.Date;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms.RecurringAlarm;


public class RecurringEditActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    EditText alertName;
    TextView depart;
    TextView busLine;
    TextView destination;
    EditText time;
    TextView direction;
    EditText numStops;
    CheckBox[] repeat;
    StringBuilder sb = new StringBuilder("1111100");

    public static final String TAG = RecurringEditActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "creating activity");

        setContentView(R.layout.activity_recurring_edit);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.recurringEditToolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



        final Intent intent = getIntent();
        alertName = (EditText) findViewById(R.id.recAlertLabel);
        depart = (TextView) findViewById(R.id.recDepartingFrom);
        busLine = (TextView) findViewById(R.id.recBusLine);
        destination = (TextView) findViewById(R.id.textView14);
        time = (EditText) findViewById(R.id.recDepartTime);
        direction = (TextView) findViewById(R.id.recDirection);
        numStops = (EditText) findViewById(R.id.recNumStops);
        repeat = new CheckBox[7];
        repeat[0] = (CheckBox) findViewById(R.id.checkBox);
        repeat[1] = (CheckBox) findViewById(R.id.checkBox2);
        repeat[2] = (CheckBox) findViewById(R.id.checkBox3);
        repeat[3] = (CheckBox) findViewById(R.id.checkBox4);
        repeat[4] = (CheckBox) findViewById(R.id.checkBox5);
        repeat[5] = (CheckBox) findViewById(R.id.checkBox6);
        repeat[6] = (CheckBox) findViewById(R.id.checkBox7);

        final RecurringAlarm alarm = setAlarm(intent);

        Button buttonSave = (Button) findViewById(R.id.recSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveValues(alarm);
                Intent myIntent = new Intent(RecurringEditActivity.this, RecurringListActivity.class);
                myIntent.putExtra("recSaved", ""); //Optional parameters
                RecurringEditActivity.this.startActivity(myIntent);
            }
        });

        Button buttonDelete = (Button) findViewById(R.id.recDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlarm(alarm);
                Intent myIntent = new Intent(RecurringEditActivity.this, RecurringListActivity.class);
                myIntent.putExtra("recDelete", ""); //Optional parameters
                RecurringEditActivity.this.startActivity(myIntent);
            }
        });
    }

    private void deleteAlarm(RecurringAlarm alarm) {
        db.deleteAlarm(alarm);
    }


    //saved alarm
    private void loadValues(RecurringAlarm alarm) {
        depart.setText(alarm.get_depart_stop());
        busLine.setText(alarm.get_bus_line());
        direction.setText(alarm.get_direction());
        numStops.setText(String.valueOf(alarm.get_stops_before()));
        destination.setText(alarm.get_dest_stop());
        time.setText(alarm.get_time());
        sb = new StringBuilder(alarm.get_repeat());
        char[] ca = sb.toString().toCharArray();

        for (int i = 0; i < 7; i++){

            if(ca[i] == '1'){
                repeat[i].setChecked(true);
            }
            else {
                repeat[i].setChecked(false);
            }
        }
    }

    //new alarm
    private void loadValues(Intent intent) {
        Log.d(TAG, "loading values");
        depart.setText(intent.getStringExtra("stopName"));
        busLine.setText(intent.getStringExtra("line"));
        direction.setText("Inbound");
        numStops.setText(intent.getStringExtra("numStops"));
        destination.setText(intent.getStringExtra("destination"));

    }

    private RecurringAlarm setAlarm(Intent intent) {
        RecurringAlarm alarm = null;
        if(intent.hasExtra("newAlert")) {
            alertName.setText("New Alert");
            loadValues(intent);
        }
        else if(intent.hasExtra("alertName")) {
            String aName = intent.getStringExtra("alertName");
            alertName.setText(aName);
            alarm = db.getAlarm(aName);
            loadValues(alarm);
        }
        return alarm;
    }



    private void saveValues(RecurringAlarm alarm){
        if (alarm == null){
            alarm = new RecurringAlarm();
            alarm.set_alert_name(alertName.getText().toString());
            alarm.set_depart_stop(depart.getText().toString());
            alarm.set_bus_line(busLine.getText().toString());
            alarm.set_dest_stop(destination.getText().toString());
            alarm.set_direction("Inbound");
            alarm.set_stops_before(Integer.parseInt(numStops.getText().toString()));
            alarm.set_repeat(sb.toString());
            alarm.set_time(time.getText().toString());
            db.addAlarm(alarm);
        }

        else {
            alarm.set_alert_name(alertName.getText().toString());
            alarm.set_depart_stop(depart.getText().toString());
            alarm.set_bus_line(busLine.getText().toString());
            alarm.set_dest_stop(destination.getText().toString());
            alarm.set_direction("Outbound");
            alarm.set_stops_before(Integer.parseInt(numStops.getText().toString()));
            alarm.set_repeat(sb.toString());
            alarm.set_time(time.getText().toString());
            db.updateAlarm(alarm);

        }


    }
}
