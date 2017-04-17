package edu.pitt.cs.cs1635.anp147.sleepycommuters.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.pitt.cs.cs1635.anp147.sleepycommuters.R;
import edu.pitt.cs.cs1635.anp147.sleepycommuters.portAuthority.Line;

/**
 * Created by austinpilz on 4/17/17.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.MyViewHolder> {
    private List<Line> lineList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.lineNumber);
            genre = (TextView) view.findViewById(R.id.lineDescription);
        }
    }


    public LineAdapter(List<Line> lines) {
        this.lineList = lines;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Line line = lineList.get(position);
        holder.title.setText(line.getLineNumber() + " " + line.getLineLetter());
        holder.genre.setText(line.getLineDescription());
    }

    @Override
    public int getItemCount() {
        return lineList.size();
    }


}
