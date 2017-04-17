package edu.pitt.cs.cs1635.anp147.sleepycommuters;

/**
 * Created by yvl2 on 4/17/17.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms.RecurringAlarm;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "alarmsManager";

    // Alarms table name
    private static final String TABLE_ALARMS = "alarms";

    // Alarms Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ALERT_NAME = "alert_name";
    private static final String KEY_DEPART_STOP = "depart_stop";
    private static final String KEY_BUS_LINE = "bus_line";
    private static final String KEY_DEST_STOP = "dest_stop";
    private static final String KEY_DIRECTION = "direction";
    private static final String KEY_STOPS_BEFORE = "stops_before";
    private static final String KEY_REPEAT = "repeat";
    private static final String KEY_HOUR = "hour";
    private static final String KEY_MINUTE = "minute";
    private static final String KEY_AMPM = "ampm";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ALARMS_TABLE = "CREATE TABLE " + TABLE_ALARMS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ALERT_NAME + " TEXT," + KEY_DEPART_STOP + " TEXT," + KEY_BUS_LINE + " TEXT," + KEY_DEST_STOP + " TEXT," + KEY_DIRECTION + " TEXT," + KEY_STOPS_BEFORE + " INT," + KEY_REPEAT + " TEXT," + KEY_HOUR + " TEXT," + KEY_MINUTE + " TEXT," + KEY_AMPM + " TEXT" + ")";
        db.execSQL(CREATE_ALARMS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ALARMS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new alarm
    void addAlarm(RecurringAlarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALERT_NAME, alarm.get_alert_name()); 
        values.put(KEY_DEPART_STOP, alarm.get_depart_stop());
        values.put(KEY_BUS_LINE, alarm.get_bus_line());
        values.put(KEY_DEST_STOP, alarm.get_dest_stop());
        values.put(KEY_DIRECTION, alarm.get_direction());
        values.put(KEY_STOPS_BEFORE, alarm.get_stops_before());
        values.put(KEY_REPEAT, alarm.get_repeat());
        values.put(KEY_HOUR, alarm.get_hour());
        values.put(KEY_MINUTE, alarm.get_minute());
        values.put(KEY_AMPM, alarm.get_ampm());


        // Inserting Row
        db.insert(TABLE_ALARMS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single alarm
    RecurringAlarm getAlarm(String alarm_name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ALARMS, new String[] { KEY_ID, KEY_ALERT_NAME,
                        KEY_DEPART_STOP, KEY_BUS_LINE,  KEY_DEST_STOP, KEY_DIRECTION, KEY_STOPS_BEFORE, KEY_REPEAT,
                KEY_HOUR, KEY_MINUTE, KEY_AMPM}, KEY_ALERT_NAME + "=?",
                new String[] { String.valueOf(alarm_name) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        RecurringAlarm alarm = new RecurringAlarm(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), Integer.parseInt(cursor.getString(6)), cursor.getString(7),
            cursor.getString(8), cursor.getString(9), cursor.getString(10));
        // return alarm
        return alarm;
    }


    // Getting All Alarms
    public List<RecurringAlarm> getAllAlarms() {
        List<RecurringAlarm> alarmList = new ArrayList<RecurringAlarm>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ALARMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                RecurringAlarm alarm = new RecurringAlarm();
                alarm.setID(Integer.parseInt(cursor.getString(0)));
                alarm.set_alert_name(cursor.getString(1));
                alarm.set_depart_stop(cursor.getString(2));
                alarm.set_bus_line(cursor.getString(3));
                alarm.set_dest_stop(cursor.getString(4));
                alarm.set_direction(cursor.getString(5));
                alarm.set_stops_before(Integer.parseInt(cursor.getString(6)));
                alarm.set_repeat(cursor.getString(7));
                alarm.set_hour(cursor.getString(8));
                alarm.set_minute(cursor.getString(9));
                alarm.set_ampm(cursor.getString(10));


                // Adding alarm to list
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }

        // return alarm list
        return alarmList;
    }

    // Updating single alarm
    public int updateAlarm(RecurringAlarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ALERT_NAME, alarm.get_alert_name());
        values.put(KEY_DEPART_STOP, alarm.get_depart_stop());
        values.put(KEY_BUS_LINE, alarm.get_bus_line());
        values.put(KEY_DEST_STOP, alarm.get_dest_stop());
        values.put(KEY_DIRECTION, alarm.get_direction());
        values.put(KEY_STOPS_BEFORE, alarm.get_stops_before());
        values.put(KEY_REPEAT, alarm.get_repeat());
        values.put(KEY_HOUR, alarm.get_hour());
        values.put(KEY_MINUTE, alarm.get_minute());
        values.put(KEY_AMPM, alarm.get_ampm());

        // updating row
        return db.update(TABLE_ALARMS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(alarm.getID()) });
    }

    // Deleting single alarm
    public void deleteAlarm(RecurringAlarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ALARMS, KEY_ID + " = ?",
                new String[] { String.valueOf(alarm.getID()) });
        db.close();
    }


    // Getting alarms Count
    public int getAlarmsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_ALARMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}