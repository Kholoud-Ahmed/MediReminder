package com.iti.www.medireminder.listadapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.www.medireminder.R;
import com.iti.www.medireminder.activities.EditMedicine;
import com.iti.www.medireminder.adapter.MedicineAdapter;
import com.iti.www.medireminder.listcaches.AlarmListCache;
import com.iti.www.medireminder.listcaches.MedicineListCashe;

import java.util.ArrayList;

/**
 * Created by KHoloud on 4/21/2016.
 */
public class AlarmArrayListAdapter extends ArrayAdapter {

    Context context;
    ArrayList<String> medicinesName;
    ArrayList<Bitmap> imageValues;
    ArrayList<Integer> medicinesId;
    AlarmListCache viewCashe;

    public AlarmArrayListAdapter(Context context, ArrayList<String> medicinesName, ArrayList<Bitmap> images, ArrayList<Integer> medicinesId) {
        super(context, R.layout.activity_single_alarm_list_row, R.id.tvSingleAlarmListMedicineName,medicinesName);

        this.context = context;
        this.medicinesName= medicinesName;
        this.imageValues = images;
        this.medicinesId = medicinesId;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View rowView = convertView;
        final MedicineAdapter medicineAdapter = new MedicineAdapter(context);
        final int positionCopy = position;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_single_alarm_list_row, parent, false);
            viewCashe = new AlarmListCache(rowView);
            viewCashe.setImageView((ImageView) rowView.findViewById(R.id.ivSingleAlarmListMedicineImage));
            viewCashe.setTitleView((TextView) rowView.findViewById(R.id.tvSingleAlarmListMedicineName));
            viewCashe.setMedcineId((TextView) rowView.findViewById(R.id.tvSingleAlarmListMedicineId));
            viewCashe.setDeleteTextView((TextView) rowView.findViewById(R.id.tvSingleAlarmListDeleteMedicine));

            rowView.setTag(viewCashe);
        } else {
            viewCashe = (AlarmListCache) rowView.getTag();
        }
        final TextView textViewMedicindeId = (TextView) rowView.findViewById(R.id.tvSingleAlarmListMedicineId);
        if(position != -1 && position<imageValues.size()) {
            viewCashe.getImageValues().setImageBitmap(imageValues.get(position));
        }else{
            viewCashe.getImageValues().setImageResource(R.drawable.default_iamge);
        }
        viewCashe.getTitleValues().setText(medicinesName.get(position));
        viewCashe.getMedcineId().setText(medicinesId.get(position) + "");
        viewCashe.getDeleteTextView().setText("Delete");
        viewCashe.getDeleteTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                medicineAdapter.deleteMedicine(medicinesId.get(position));
                AlarmArrayListAdapter.this.notifyDataSetChanged();
                medicinesName.remove(position);
                imageValues.remove(position);
                medicinesId.remove(position);
                notifyDataSetChanged();
            }
        });
        return rowView;
    }

}
