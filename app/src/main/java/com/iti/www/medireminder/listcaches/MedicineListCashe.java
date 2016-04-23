package com.iti.www.medireminder.listcaches;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iti.www.medireminder.R;

/**
 * Created by KHoloud on 4/12/2016.
 */
public class MedicineListCashe {

    private View baseView;
    TextView titleView;
    ImageView imageView;
    ImageView deleteImageView;
    ImageView editImageView;
    TextView medcineId;

    public void setTitleView(TextView titleView) {
        this.titleView = titleView;
    }


    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setDeleteImageView(ImageView deleteImageView) {
        this.deleteImageView = deleteImageView;
    }

    public void setEditImageView(ImageView editImageView) {
        this.editImageView = editImageView;
    }

    public void setMedcineId(TextView medcineId) {
        this.medcineId = medcineId;
    }

    public TextView getMedcineId() {
        if(medcineId == null){
            medcineId = (TextView) baseView.findViewById(R.id.tvSingleListMedicineId);
        }
        return medcineId;
    }

    public  View getBaseView(){
        return baseView;
    }

    public TextView getTitleValues() {
        if(titleView == null){
            titleView = (TextView) baseView.findViewById(R.id.tvSingleListMedicineName);
        }
        return titleView;
    }

    public ImageView getImageValues() {
        if(imageView == null){
            imageView = (ImageView) baseView.findViewById(R.id.ivSingleListMedicineImage);
        }
        return imageView;
    }

    public ImageView getDeleteImageView() {
        if(deleteImageView == null){
            deleteImageView = (ImageView) baseView.findViewById(R.id.ivSingleListDeleteMedicine);
        }
        return deleteImageView;
    }

    public ImageView getEditImageView() {
        if(editImageView == null){
            editImageView = (ImageView) baseView.findViewById(R.id.ivSingleListEditMedicine);
        }
        return editImageView;
    }

    public MedicineListCashe(View baseView){
        this.baseView = baseView;
//        deleteImageView = (ImageView) baseView.findViewById(R.id.ivSingleListDeleteMedicine);
//        deleteImageView.setOnClickListener(this);
//        editImageView = (ImageView) baseView.findViewById(R.id.ivSingleListEditMedicine);
//        editImageView.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View v) {
//        MedicineAdapter medicineAdapter = new MedicineAdapter(baseView.getContext());
//        switch(v.getId()){
//            case R.id.ivSingleListDeleteMedicine:
//                medicineAdapter.deleteMedicine(Integer.parseInt(medcineId.getText().toString()));
//                break;
//
//            case R.id.ivSingleListEditMedicine:
//                Intent intentToEditMedicine = new Intent(baseView.getContext(), EditMedicine.class);
//                intentToEditMedicine.putExtra("MedicineId",Integer.parseInt(medcineId.getText().toString()));
//                baseView.getContext().startActivity(intentToEditMedicine);
//                break;
//        }
//
//    }
}
