package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RecurringListActivity extends AppCompatActivity {

    private ListView lv;
    private List<RecurringAlarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_list);
        final Intent intent = getIntent();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_reccuring);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent myIntent = new Intent(RecurringListActivity.this, MapsActivity.class);
                myIntent.putExtra("createRecurringInstruction", ""); //Optional parameters
                RecurringListActivity.this.startActivity(myIntent);

            }
        });

        lv = (ListView) findViewById(R.id.recurringAlertList);

        DatabaseHandler db = new DatabaseHandler(this);

        alarms = db.getAllAlarms();

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        for (RecurringAlarm alarm : alarms) {
            your_array_list.add(alarm.get_hour() + ":" + alarm.get_minute() + " " + alarm.get_ampm() + " | " + alarm.get_alert_name());
        }

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                                    long arg3){
                String value = (String)adapter.getItemAtPosition(position);
                String name = getName(value);
                Intent newIntent = new Intent(RecurringListActivity.this,RecurringEditActivity.class);
                newIntent.putExtra("alertName", name);
                startActivity(newIntent);
            }
        });

        if (intent.hasExtra("recSaved"))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RecurringListActivity.this).create();
            alertDialog.setTitle("Alert Saved!");
            alertDialog.setMessage("Your recurring alert has been saved successfully!");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }

    private String getName(String alertTimeName) {

        String alertName = "Not Found";
        int i = alertTimeName.indexOf("|");
        if ( i!= -1) {
            alertName = alertTimeName.substring(i + 2);
        }
        return alertName;
    }
}
