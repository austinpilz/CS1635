package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Adapter.LineAdapter;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.listener.ItemClickSupport;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.portAuthority.Line;

public class LineSelectionActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView lv;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_selection);


        final Intent intent = getIntent();
        TextView textView = (TextView) findViewById(R.id.lineSelectionHeader);
        textView.setText("To create an alarm, please select one of the bus lines that runs through " + intent.getStringExtra("stopName"));

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);




        recyclerView = (RecyclerView) findViewById(R.id.lineSelectList);

        //Ideally, this list would be pulled from the port authority API
        final List<Line> lineArray = new ArrayList<Line>();
        lineArray.add(new Line("71", "A", "Downtown"));
        lineArray.add(new Line("71", "B", "Murrysville"));
        lineArray.add(new Line("71", "C", "Robinson"));
        lineArray.add(new Line("71", "D", "Bellevue"));
        lineArray.add(new Line("61", "A", "Waterfront"));
        lineArray.add(new Line("61", "B", "Southside"));
        lineArray.add(new Line("61", "C", "Oakland"));
        lineArray.add(new Line("61", "D", "Narnia"));
        lineArray.add(new Line("51", "A", "Waterfront"));
        lineArray.add(new Line("51", "B", "Southside"));
        lineArray.add(new Line("51", "C", "Oakland"));
        lineArray.add(new Line("51", "D", "Narnia"));
        lineArray.add(new Line("41", "A", "Waterfront"));
        lineArray.add(new Line("41", "B", "Southside"));
        lineArray.add(new Line("41", "C", "Oakland"));
        lineArray.add(new Line("41", "D", "Narnia"));

        LineAdapter lineAdapter = new LineAdapter(lineArray);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(lineAdapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                Intent newIntent = new Intent(LineSelectionActivity.this,DestinationSelectActivity.class);
                newIntent.putExtra("stopName", intent.getStringExtra("stopName"));
                newIntent.putExtra("line", lineArray.get(position).getLineNumber() + " " + lineArray.get(position).getLineLetter() + " | " + lineArray.get(position).getLineDescription());
                startActivity(newIntent);
            }
        });




        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );
                */

        //lv = (ListView) findViewById(R.id.lineSelectList);
        //lv.setAdapter(arrayAdapter);

        /*
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
        */
    }

}
