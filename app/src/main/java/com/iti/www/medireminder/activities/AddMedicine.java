package com.iti.www.medireminder.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iti.www.medireminder.R;
import com.iti.www.medireminder.adapter.MedicineAdapter;
import com.iti.www.medireminder.dto.Medicine;
import com.iti.www.medireminder.utils.DbBitmapUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMedicine extends Activity implements View.OnClickListener{

    TextView id;
    EditText name;
    ImageView image;
    EditText dose;
    EditText date;
    EditText time;
    EditText repitation;
    Spinner types;
    Medicine medicine;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    Button addMedicine;
    MedicineAdapter medicineAdapter;
    String selectedType;
    String timeFromTmePicker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        initComponants();
        addListeners();
        addItemsOnMedicineTypesSpinner();
        addListenerOnSpinnerItemSelection();

    }

    private void addListenerOnSpinnerItemSelection() {
        types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedType = "Liquied";
            }
        });
    }

    private void addItemsOnMedicineTypesSpinner() {
        List<String> list = new ArrayList<String>();
        list.add("Liquid");
        list.add("Tablet");
        list.add("Capsules");
        list.add("Injections");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        types.setAdapter(dataAdapter);
    }

    private void addListeners() {
        image.setOnClickListener(this);
        time.setOnClickListener(this);
        date.setOnClickListener(this);
        addMedicine.setOnClickListener(this);
    }

    private void initComponants() {
        id = (TextView) findViewById(R.id.etAddMedicineId);
        name = (EditText) findViewById(R.id.etAddMedicineName);
        image = (ImageView) findViewById(R.id.ivAddMedicineImage);
        dose = (EditText) findViewById(R.id.etAddMedicineDose);
        date = (EditText) findViewById(R.id.etAddMedicineDate);
        time = (EditText) findViewById(R.id.etAddMedicineTime);
        repitation = (EditText) findViewById(R.id.etAddMedicineRepitation);
        addMedicine = (Button) findViewById(R.id.bAddMedicineAdd);
        types = (Spinner) findViewById(R.id.sAddMedicineTypes);
        image.setImageResource(R.drawable.default_iamge);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            image.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case (R.id.ivAddMedicineImage):
                Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(captureImage.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(captureImage,REQUEST_IMAGE_CAPTURE);
                }

                break;

            case (R.id.etAddMedicineDate):

                final Calendar mcurrentDate = Calendar.getInstance();
                int year = mcurrentDate.get(Calendar.YEAR);
                final int month = mcurrentDate.get(Calendar.MONTH);
                int day = mcurrentDate.get(Calendar.DAY_OF_WEEK);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText( monthOfYear+1 + "/" + dayOfMonth+"/"+year);
                    }
                }, year, month, day);
                mDatePicker.setTitle("Select Time");
                mDatePicker.show();

                break;

            case (R.id.etAddMedicineTime):
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR);
                final int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddMedicine.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY,selectedHour);
                        calendar.set(Calendar.MINUTE,selectedMinute);

                        if(selectedHour<10){
                            if(selectedMinute  <10){
                                timeFromTmePicker = "0" + selectedHour + ":0" + selectedMinute;
                                time.setText( "0"+selectedHour + ":0" + selectedMinute);
                            }else{
                                time.setText( "0"+selectedHour + ":" + selectedMinute);
                                timeFromTmePicker = "0" + selectedHour + ":" + selectedMinute;
                            }

                        }else if(selectedHour == 00){
                            if(selectedMinute  <10){
                                timeFromTmePicker = "0" + selectedHour + ":0" + selectedMinute;
                                time.setText( "0"+selectedHour + ":0" + selectedMinute);
                            }else{
                                time.setText( "0"+selectedHour + ":" + selectedMinute);
                                timeFromTmePicker = "0" + selectedHour + ":" + selectedMinute;
                            }
                        }else{
                            if(selectedMinute<10){
                                time.setText( selectedHour + ":0" + selectedMinute);
                                timeFromTmePicker =selectedHour + ":0" + selectedMinute;
                            }else{
                                time.setText( selectedHour + ":" + selectedMinute);
                                timeFromTmePicker =  selectedHour + ":" + selectedMinute;
                            }

                        }

                        //time.setText( calendar.get(Calendar.HOUR_OF_DAY) + ":" + selectedMinute);

                        Toast.makeText(getApplicationContext(),selectedHour+":"+selectedMinute+"",Toast.LENGTH_LONG).show();
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(mcurrentTime.get(Calendar.YEAR),mcurrentTime.get(Calendar.WEEK_OF_MONTH), mcurrentTime.get(Calendar.DAY_OF_MONTH) , selectedHour, selectedMinute, 0);

//                        Log.i("*********** time before", timeInMilliSeconds+"");

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;

            case (R.id.bAddMedicineAdd):
                medicine = new Medicine();
                medicine.setMedicineName(name.getText().toString());
                medicine.setMedicineType(selectedType);
                //////////////////// new ///////////////////
                String mDose = dose.getText().toString().trim();
                Log.i("***************",mDose + " " + mDose.length());
                if(mDose.equals("") || mDose.length() <= 0) {
                    Toast.makeText(AddMedicine.this, "Please enter medicine Dose",Toast.LENGTH_LONG).show();
                }else{
                    medicine.setMedicineDose(Integer.parseInt(dose.getText().toString()));
                }
                ////////////////////////////////////////////
                if(imageBitmap != null){
                    medicine.setMedicineImage(DbBitmapUtil.getBytes(imageBitmap));
                }
                ////////////////////////////////////////////// new
                else{
                    imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.default_iamge);
                    medicine.setMedicineImage(DbBitmapUtil.getBytes(imageBitmap));
                }
                ////////////////////////////////////////////////////
                medicine.setMedicineDate(date.getText().toString());
                medicine.setMedicineTime(timeFromTmePicker);
                medicine.setMedicineRepition(Integer.parseInt(repitation.getText().toString()));

                ////////////////////// new ///////////////////////////////////////
                medicineAdapter = new MedicineAdapter(AddMedicine.this);
                Medicine m2 = new Medicine();
                m2 = medicineAdapter.selectMedicine(medicine.getMedicineName());
                Log.i("********** ID",m2.getMedicineId()+"");
                if(m2.getMedicineName() != null) {
                    Toast.makeText(AddMedicine.this, "Can not add this medicine "+m2.getMedicineName(), Toast.LENGTH_LONG).show();
                }else {
                    medicineAdapter.insertMedicine(medicine);
                    m2 = medicineAdapter.selectMedicine(medicine.getMedicineName());
                    Toast.makeText(AddMedicine.this, "Added "+m2, Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
}
