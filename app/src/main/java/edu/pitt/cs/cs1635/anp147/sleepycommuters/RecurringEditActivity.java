package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RecurringEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_edit);

        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.recAlertLabel);
        textView.setText(intent.getStringExtra("alertName"));

        Button buttonSave = (Button) findViewById(R.id.recSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
}
