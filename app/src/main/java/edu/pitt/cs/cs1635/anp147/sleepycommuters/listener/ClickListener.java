package edu.pitt.cs.cs1635.anp147.sleepycommuters.listener;

import android.view.View;

/**
 * Created by austinpilz on 4/17/17.
 */

public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
