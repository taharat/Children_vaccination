package com.example.mow.childrenvaccine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mow on 6/25/2015.
 */


public class  CustomVaccineAdapter extends ArrayAdapter<Vaccine> {
    LayoutInflater inflater;

    public CustomVaccineAdapter(Context context, List<Vaccine> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_vaccine, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Vaccine list = getItem(position);
        holder.tvName.setText(list.getVaccineName() + "");
        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
    }
}