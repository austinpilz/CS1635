package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecurringEditActivity extends AppCompatActivity {

    DatabaseHandler db = new DatabaseHandler(this);
    TextView alertName;
    TextView depart;
    TextView bus_line;
    //TextView time;
    TextView direction;
    TextView stops_before;
    //TextView repeat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_edit);
        final Intent intent = getIntent();
        alertName = (TextView) findViewById(R.id.recAlertLabel);
        depart = (TextView) findViewById(R.id.recDepartingFrom);
        bus_line = (TextView) findViewById(R.id.recBusLine);
        //TextView time = (TextView) findViewById(R.id.recAlertLabel);
        direction = (TextView) findViewById(R.id.recDirection);
        stops_before = (TextView) findViewById(R.id.recNumStops);
        //TextView repeat = (TextView) findViewById(R.id.recAlertLabel);
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
                Intent myIntent = new Intent(RecurringEditActivity.this, RecurringListActivity.class);
                myIntent.putExtra("recDelete", ""); //Optional parameters
                RecurringEditActivity.this.startActivity(myIntent);
            }
        });
    }



    private void loadValues(RecurringAlarm alarm) {

        depart.setText(alarm.get_depart_stop());
        bus_line.setText(alarm.get_bus_line());
        direction.setText(alarm.get_direction());
//        stops_before.setText(alarm.get_stops_before());

    }

    private RecurringAlarm setAlarm(Intent intent) {
        RecurringAlarm alarm = null;
        if(intent.hasExtra("newAlert")) {
            alertName.setText("New Alert");
            loadValues();
        }
        else if(intent.hasExtra("alertName")) {
            String aName = intent.getStringExtra("alertName");
            alertName.setText(aName);
            alarm = db.getAlarm(aName);
            loadValues(alarm);
        }
        return alarm;
    }

    //new alarm
    private void loadValues() {
        //load values from intent
    }

    private void saveValues(RecurringAlarm alarm){
        if (alarm == null){
            alarm = new RecurringAlarm();
            alarm.set_alert_name(alertName.getText().toString());
            alarm.set_depart_stop(depart.getText().toString());
            alarm.set_bus_line(bus_line.getText().toString());
            alarm.set_direction("Inbound");
            alarm.set_stops_before(Integer.parseInt(stops_before.getText().toString()));
            alarm.set_repeat("0110011");
            alarm.set_hour("7");
            alarm.set_minute("30");
            alarm.set_ampm("AM");
            db.addAlarm(alarm);
        }

        else {
            alarm.set_alert_name("SAVED ALERT");
            alarm.set_depart_stop(depart.getText().toString());
            alarm.set_bus_line(bus_line.getText().toString());
            alarm.set_direction("Outbound");
            alarm.set_stops_before(Integer.parseInt(stops_before.getText().toString()));
            alarm.set_repeat("0110011");
            alarm.set_hour("7");
            alarm.set_minute("30");
            alarm.set_ampm("PM");
            db.updateAlarm(alarm);

        }


    }
}
