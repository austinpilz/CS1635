package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class LineSelectionActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_selection);


        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.lineSelectionHeader);
        textView.setText("Showing lines for stop " + intent.getStringExtra("stopName"));

        lv = (ListView) findViewById(R.id.lineSelectList);

        // Instanciating an array list (you don't need to do this,
        // you already have yours).
        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("71A | Dahntahn");
        your_array_list.add("71B | Elmsworth");
        your_array_list.add("71C | East Carson");
        your_array_list.add("71D | South Side");

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

                Intent newIntent = new Intent(LineSelectionActivity.this,DestinationSelectActivity.class);
                newIntent.putExtra("stopName", intent.getStringExtra("stopName"));
                newIntent.putExtra("line", value);
                startActivity(newIntent);
            }
        });
    }

}
