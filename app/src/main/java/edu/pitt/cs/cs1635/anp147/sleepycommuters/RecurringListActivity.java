package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class RecurringListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.recurringCreateButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(RecurringListActivity.this, MapsActivity.class);
                myIntent.putExtra("createRecurringInstruction", ""); //Optional parameters
                RecurringListActivity.this.startActivity(myIntent);

            }
        });

        final Intent intent = getIntent();
        if (intent.hasExtra("recurringCreated"))
        {
            Toast.makeText(RecurringListActivity.this, "Your recurring alert has been created!", Toast.LENGTH_LONG).show();
        }
    }

}
