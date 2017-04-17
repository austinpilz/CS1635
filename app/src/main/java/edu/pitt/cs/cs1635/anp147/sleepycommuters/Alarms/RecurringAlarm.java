package edu.pitt.cs.cs1635.anp147.sleepycommuters.Alarms;

/**
 * Created by yvl2 on 4/17/17.
 */

public class RecurringAlarm {

    //private variables
    int _id;
    String _alert_name;
    String _depart_stop;
    String _bus_line;
    String _dest_stop;
    String _direction;
    int _stops_before;
    String _repeat;
    String _time;
//    String _minute;
//    String _ampm;

    // Empty constructor
    public RecurringAlarm(){

    }
    // constructor


    public RecurringAlarm(int _id, String _alert_name, String _depart_stop, String _bus_line, String _dest_stop, String _direction, int _stops_before, String _repeat, String _time) {
        this._id = _id;
        this._alert_name = _alert_name;
        this._depart_stop = _depart_stop;
        this._bus_line = _bus_line;
        this._dest_stop = _dest_stop;
        this._direction = _direction;
        this._stops_before = _stops_before;
        this._repeat = _repeat;
        this._time = _time;
//        this._minute = _minute;
//        this._ampm = _ampm;
    }

    // getting ID
    public int getID(){
        return this._id;
    }

    // setting id
    public void setID(int id){
        this._id = id;
    }
    public String get_alert_name() {
        return _alert_name;
    }

    public void set_alert_name(String _alert_name) {
        this._alert_name = _alert_name;
    }

    public String get_depart_stop() {
        return _depart_stop;
    }

    public void set_depart_stop(String _depart_stop) {
        this._depart_stop = _depart_stop;
    }

    public String get_bus_line() {
        return _bus_line;
    }

    public void set_bus_line(String _bus_line) {
        this._bus_line = _bus_line;
    }

    public String get_dest_stop() {
        return _dest_stop;
    }

    public void set_dest_stop(String _dest_stop) {
        this._dest_stop = _dest_stop;
    }

    public String get_direction() {
        return _direction;
    }

    public void set_direction(String _direction) {
        this._direction = _direction;
    }

    public int get_stops_before() {
        return _stops_before;
    }

    public void set_stops_before(int _stops_before) {
        this._stops_before = _stops_before;
    }

    public String get_repeat() {
        return _repeat;
    }

    public void set_repeat(String _repeat) {
        this._repeat = _repeat;
    }

//    public String get_hour() {
//        return _hour;
//    }
//
//    public void set_hour(String _hour) {
//        this._hour = _hour;
//    }
//
//    public String get_minute() {
//        return _minute;
//    }
//
//    public void set_minute(String _minute) {
//        this._minute = _minute;
//    }
//
//    public String get_ampm() {
//        return _ampm;
//    }
//
//    public void set_ampm(String _ampm) {
//        this._ampm = _ampm;
//    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
