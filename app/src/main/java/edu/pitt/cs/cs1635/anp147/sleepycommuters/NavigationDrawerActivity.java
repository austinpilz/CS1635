package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Adapter.AlarmAdapter;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms.RecurringAlarm;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.listener.ItemClickSupport;

import static android.R.attr.value;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lv;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_startButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show(); */
                Intent myIntent = new Intent(NavigationDrawerActivity.this, MapsActivity.class);
                NavigationDrawerActivity.this.startActivity(myIntent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        recyclerView = (RecyclerView) findViewById(R.id.homeSelectList);

        //Pull recurring alarms from the database
        DatabaseHandler db = new DatabaseHandler(this);
        List<RecurringAlarm> alarms = db.getAllAlarms();

        if (alarms.size() > 0) {

            AlarmAdapter alarmAdapter = new AlarmAdapter(alarms);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(alarmAdapter);

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    Intent newIntent = new Intent(NavigationDrawerActivity.this, MapsActivity.class);
                    newIntent.putExtra("recBegin", value);
                    startActivity(newIntent);
                }
            });
        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home)
        {
            //Display Map
            Intent myIntent = new Intent(NavigationDrawerActivity.this, MapsActivity.class);
            NavigationDrawerActivity.this.startActivity(myIntent);
        } else if (id == R.id.nav_recurring)
        {
            //Display Recurring
            Intent myIntent = new Intent(NavigationDrawerActivity.this, RecurringListActivity.class);
            NavigationDrawerActivity.this.startActivity(myIntent);
        }
        else if (id == R.id.nav_settings)
        {
            //Display Recurring
            Intent myIntent = new Intent(NavigationDrawerActivity.this, Test.class);
            NavigationDrawerActivity.this.startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
