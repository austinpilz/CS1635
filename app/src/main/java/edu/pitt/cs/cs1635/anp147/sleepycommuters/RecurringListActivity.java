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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_list);
        final Intent intent = getIntent();


        lv = (ListView) findViewById(R.id.recurringAlertList);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("8:00AM | Work 1");
        your_array_list.add("8:15 AM | Over Slept");
        your_array_list.add("9:00 AM | Dude");
        your_array_list.add("7:00 PM | Night Class");

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

                Intent newIntent = new Intent(RecurringListActivity.this,RecurringEditActivity.class);
                newIntent.putExtra("alertName", value);
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
}
