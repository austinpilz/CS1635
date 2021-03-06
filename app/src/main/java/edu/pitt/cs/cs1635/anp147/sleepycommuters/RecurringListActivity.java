package edu.pitt.cs.cs1635.anp147.sleepycommuters;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import java.util.List;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Adapter.RecurringListAlarmAdapter;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms.RecurringAlarm;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.listener.ItemClickSupport;

public class RecurringListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<RecurringAlarm> alarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurring_list);
        final Intent intent = getIntent();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.recurringListToolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

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

        recyclerView = (RecyclerView) findViewById(R.id.recRecView);

        //Pull recurring alarms from the database
        DatabaseHandler db = new DatabaseHandler(this);
        final List<RecurringAlarm> alarms = db.getAllAlarms();

        if (alarms.size() > 0) {

            RecurringListAlarmAdapter alarmAdapter = new RecurringListAlarmAdapter(alarms);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(alarmAdapter);

            ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    RecurringAlarm value = alarms.get(position);

                    //CHANGE
                    Intent newIntent = new Intent(RecurringListActivity.this, RecurringEditActivity.class);
                    newIntent.putExtra("alertName", value.get_alert_name());
                    startActivity(newIntent);
                }
            });
        }
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
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendNotificationLeaveHouse();
                }
            }, 5000);

            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    sendNotificationBusClose();
                }
            }, 10000);
        }
        else if (intent.hasExtra("recDelete"))
        {
            AlertDialog alertDialog = new AlertDialog.Builder(RecurringListActivity.this).create();
            alertDialog.setTitle("Alert Deleted!");
            alertDialog.setMessage("Your recurring alert has been deleted successfully!");
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

    public void sendNotificationLeaveHouse() {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(RecurringListActivity.this, NavigationDrawerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.ic_home_black_24dp);
        mBuilder.setContentTitle("Sleepy Commuter");
        mBuilder.setContentText("Leave your house to catch that bus!");

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(001, mBuilder.build());
    }

    public void sendNotificationBusClose(){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);

        //Create the intent that’ll fire when the user taps the notification//

        Intent intent = new Intent(RecurringListActivity.this, NavigationDrawerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        mBuilder.setContentIntent(pendingIntent);

        mBuilder.setSmallIcon(R.drawable.ic_airport_shuttle_black_24dp);
        mBuilder.setContentTitle("Sleepy Commuter");
        mBuilder.setContentText("The bus is 2 stops away!");

        NotificationManager mNotificationManager =

                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(002, mBuilder.build());
    }

}

