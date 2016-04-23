package com.iti.www.medireminder.listcaches;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.www.medireminder.R;

/**
 * Created by KHoloud on 4/21/2016.
 */
public class AlarmListCache {
    private View baseView;
    TextView titleView;
    ImageView imageView;
    TextView tvDelete;
    TextView medcineId;

    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }


    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDeleteTextView(TextView deleteTv) {
        this.tvDelete = tvDelete;
    }

    public void setMedcineId(TextView medcineId) {
        this.medcineId = medcineId;
    }

    public TextView getMedcineId() {
        if (medcineId == null) {
            medcineId = (TextView) baseView.findViewById(R.id.tvSingleAlarmListMedicineId);
        }
        return medcineId;
    }

    public View getBaseView() {
        return baseView;
    }

    public TextView getTitleValues() {
        if (titleView == null) {
            titleView = (TextView) baseView.findViewById(R.id.tvSingleAlarmListMedicineName);
        }
        return titleView;
    }

    public ImageView getImageValues() {
        if (imageView == null) {
            imageView = (ImageView) baseView.findViewById(R.id.ivSingleAlarmListMedicineImage);
        }
        return imageView;
    }

    public TextView getDeleteTextView() {
        if (tvDelete == null) {
            tvDelete = (TextView) baseView.findViewById(R.id.tvSingleAlarmListDeleteMedicine);
        }
        return tvDelete;
    }

    public AlarmListCache(View baseView) {
        this.baseView = baseView;
    }
}