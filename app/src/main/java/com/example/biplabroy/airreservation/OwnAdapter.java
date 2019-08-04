package com.example.biplabroy.airreservation;
import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;
public class OwnAdapter extends BaseAdapter {
    ArrayList<Details> data = new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    OwnAdapter(Context context, ArrayList<Details> data) {
        this.context = context;
        this.data = data;
        this.inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        final View view = inflater.inflate(R.layout.items, null);
        ((TextView) view.findViewById(R.id.tvname)).setText(this.data.get(position).name);
        ((TextView) view.findViewById(R.id.tvphone)).setText(this.data.get(position).phone);
        ((TextView) view.findViewById(R.id.dat)).setText(this.data.get(position).date + " | " + this.data.get(position).time);
        String s = "from " + this.data.get(position).from + " to " + this.data.get(position).to + " \n" + this.data.get(position).not + " tickets " + this.data.get(position).price + "/- per head";
        ((TextView) view.findViewById(R.id.detail)).setText(s);
        ImageView imcall = (ImageView) view.findViewById(R.id.iv);
        imcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, 0);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Want to call?");
                    builder.setMessage("Are you sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data.get(position).phone));
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            context.startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();
                }
            }
        });
        return view;
    }
}
