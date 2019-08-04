package com.example.biplabroy.airreservation;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
public class FlightAdapter extends BaseAdapter{
    ArrayList<FlightDetails> fd;
    Context context;
    LayoutInflater inflater;
    FlightAdapter(Context context, ArrayList<FlightDetails> fd){
        this.context = context;
        this.fd = fd;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return fd.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_of_flights,null);
        TextView travel = (TextView) view.findViewById(R.id.travel);
        TextView time = (TextView) view.findViewById(R.id.time);
        ImageView delete = (ImageView) view.findViewById(R.id.delete);
        travel.setText(fd.get(position).from + " --> " + fd.get(position).to);
        time.setText(fd.get(position).time + "     price: " + fd.get(position).price);
        if(FlightActivity.str.equals("Main")){
            delete.setImageResource(R.drawable.ic_3d_rotation_black_24dp);
            delete.setClickable(false);
            return view;
        }
        final int pos = position;
        delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    (new Database(context)).cancleFlight(fd.get(pos).id);
                    context.startActivity(new Intent(context, FlightActivity.class).putExtra("comeFrom",FlightActivity.str));
                }
            });
        return view;
    }
}
