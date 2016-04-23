package com.iti.www.medireminder.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class EditMedicine extends Activity implements View.OnClickListener{

    TextView medicineId;
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
    Button EditMedicine;
    MedicineAdapter medicineAdapter;
    String selectedType;
    String timeFromTimePicker;
    Medicine myMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicine);


        initComponants();
        addListeners();
        addItemsOnMedicineTypesSpinner();
        addListenerOnSpinnerItemSelection();
        addComponents();
    }

    private void addComponents() {

        Intent intent = getIntent();
        int myId = intent.getIntExtra("MedicineId",-1);
        if(myId != -1){
            medicineAdapter = new MedicineAdapter(EditMedicine.this);
            myMedicine = medicineAdapter.selectMedicineById(myId);
            Log.i("++++++",medicineId+"");
            Log.i("++++++",myMedicine+"");
            medicineId.setText(myId+"");
            name.setText(myMedicine.getMedicineName());
            if(myMedicine.getMedicineImage() != null) {
                image.setImageBitmap(DbBitmapUtil.getImage(myMedicine.getMedicineImage()));
                imageBitmap = DbBitmapUtil.getImage(myMedicine.getMedicineImage());////////////////////// new
            }
            dose.setText(myMedicine.getMedicineDose()+"");
            date.setText(myMedicine.getMedicineDate());
//            String convertedTime = DateFormat.format("hh:mm:ss", myMedicine.getMedicineTime()).toString();
            String convertedTime = myMedicine.getMedicineTime();
            time.setText(convertedTime);
            repitation.setText(myMedicine.getMedicineRepition()+"");
            int selectedTypePosition;
            switch (myMedicine.getMedicineType()){
                case "Liquid":
                    selectedTypePosition = 0;
                    break;
                case "Tablet":
                    selectedTypePosition = 1;
                    break;
                case "Capsules":
                    selectedTypePosition = 2;
                    break;
                case "Injections":
                    selectedTypePosition = 3;
                    break;
                default:
                    selectedTypePosition = -1;
                    break;
            }
            types.setSelection(selectedTypePosition);
        }
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
        EditMedicine.setOnClickListener(this);
    }

    private void initComponants() {
        medicineId = (TextView) findViewById(R.id.etEditMedicineId);
        name = (EditText) findViewById(R.id.etEditMedicineName);
        image = (ImageView) findViewById(R.id.ivEditMedicineImage);
        dose = (EditText) findViewById(R.id.etEditMedicineDose);
        date = (EditText) findViewById(R.id.etEditMedicineDate);
        time = (EditText) findViewById(R.id.etEditMedicineTime);
        repitation = (EditText) findViewById(R.id.etEditMedicineRepitation);
        EditMedicine = (Button) findViewById(R.id.bEditMedicineUpdate);
        types = (Spinner) findViewById(R.id.sEditMedicineTypes);
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
            case (R.id.ivEditMedicineImage):
                Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(captureImage.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(captureImage,REQUEST_IMAGE_CAPTURE);
                }

                break;

            case (R.id.etEditMedicineDate):

                Calendar mcurrentDate = Calendar.getInstance();
                int year = mcurrentDate.get(Calendar.YEAR);
                final int month = mcurrentDate.get(Calendar.MONTH);
                int day = mcurrentDate.get(Calendar.DAY_OF_WEEK);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(EditMedicine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText( monthOfYear+1 + "/" + dayOfMonth+"/"+year);
                    }
                }, year, month, day);
                mDatePicker.setTitle("Select Time");
                mDatePicker.show();

                break;

            case (R.id.etEditMedicineTime):
                final Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditMedicine.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        /*time.setText( selectedHour + ":" + selectedMinute);

                        timeFromTimePicker = selectedHour + ":" + selectedMinute;*/

                        if(selectedHour<10){
                            if(selectedMinute  <10){
                                timeFromTimePicker = "0"+selectedHour + ":0" + selectedMinute;
                                time.setText( "0"+selectedHour + ":0" + selectedMinute);
                            }else{
                                time.setText( "0"+selectedHour + ":" + selectedMinute);
                                timeFromTimePicker = "0"+selectedHour + ":" + selectedMinute;
                            }

                        }else if(selectedHour == 00){
                            if(selectedMinute  <10){
                                timeFromTimePicker = "0" + selectedHour + ":0" + selectedMinute;
                                time.setText( "0"+selectedHour + ":0" + selectedMinute);
                            }else{
                                time.setText( "0"+selectedHour + ":" + selectedMinute);
                                timeFromTimePicker = "0" + selectedHour + ":" + selectedMinute;
                            }
                        }else{
                            if(selectedMinute<10){
                                time.setText( selectedHour + ":0" + selectedMinute);
                                timeFromTimePicker =selectedHour + ":0" + selectedMinute;
                            }else{
                                time.setText( selectedHour + ":" + selectedMinute);
                                timeFromTimePicker =  selectedHour + ":" + selectedMinute;
                            }

                        }
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
                break;

            case (R.id.bEditMedicineUpdate):
//                medicine = new Medicine();    ////////////////new
//                myMedicine.setMedicineId(Integer.parseInt(medicineId.getText().toString()));  ////////////////removed
                myMedicine.setMedicineName(name.getText().toString());
                myMedicine.setMedicineType(selectedType);
                myMedicine.setMedicineDose(Integer.parseInt(dose.getText().toString()));
//                if(imageBitmap != null) {
                    myMedicine.setMedicineImage(DbBitmapUtil.getBytes(imageBitmap));
//                }else{
//                    medicine.setMedicineImage(myMedicine.getMedicineImage());
//                }
                myMedicine.setMedicineDate(date.getText().toString());
                myMedicine.setMedicineTime(time.getText().toString()); ////////////////////////// new///////////////
                myMedicine.setMedicineRepition(Integer.parseInt(repitation.getText().toString()));

                medicineAdapter = new MedicineAdapter(EditMedicine.this);

//                medicine.setMedicineId(medicineAdapter.selectMedicine(myMedicine.getMedicineName()).getMedicineId()); //////////removed

                ////////////////////// new ///////////////////////////////////////
                medicineAdapter = new MedicineAdapter(EditMedicine.this);
                Medicine m2 = new Medicine();
                m2 = medicineAdapter.selectMedicinesByNameAndType(name.getText().toString(), selectedType);
                Log.i("********** ID", m2.getMedicineId() + "");
                if(m2.getMedicineName() != null && ! m2.getMedicineName().equals(name.getText().toString())) {
                    Toast.makeText(EditMedicine.this, "Can not add medicine "+m2.getMedicineName()+", it is already exist.", Toast.LENGTH_LONG).show();
                }else {
                    medicineAdapter.updateMedicine(myMedicine); //updated
                    m2 = medicineAdapter.selectMedicinesByNameAndType(myMedicine.getMedicineName(),selectedType);
                    Toast.makeText(EditMedicine.this, "Updated "+m2, Toast.LENGTH_LONG).show();
                }

                break;

            default:
                break;
        }
    }
}
