package com.iti.www.medireminder.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.iti.www.medireminder.R;
import com.iti.www.medireminder.adapter.MedicineAdapter;
import com.iti.www.medireminder.dto.Medicine;
import com.iti.www.medireminder.listadapters.MedicinesListArrayAdapter;
import com.iti.www.medireminder.receivers.MedicineAlarmReceiver;
import com.iti.www.medireminder.utils.AlarmUtils;
import com.iti.www.medireminder.utils.DbBitmapUtil;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeActivity extends Activity implements View.OnClickListener {

    ListView lvHomeMedicinesList;
    MedicineAdapter medicineAdapter;
    List<Medicine> medicines;
    private FloatingActionButton fab;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initComponents();
        handleAlarmOperation();
        addListeners();

//        Calendar calendar = Calendar.getInstance();
//        int selectedHour = calendar.get(Calendar.HOUR_OF_DAY);
//        int selectedMinute = calendar.get(Calendar.MINUTE);
//        String time=selectedHour+":"+selectedMinute;
//        if(selectedHour<10){
//            if(selectedMinute  <10){
//                time = "0" + selectedHour + ":0" + selectedMinute;
//            }else{
//                time = "0" + selectedHour + ":" + selectedMinute;
//            }
//
//        }else if(selectedHour == 00){
//            if(selectedMinute  <10){
//                time = "0" + selectedHour + ":0" + selectedMinute;
//            }else{
//                time = "0" + selectedHour + ":" + selectedMinute;
//            }
//        }else{
//            if(selectedMinute<10){
//                time =selectedHour + ":0" + selectedMinute;
//            }else{
//                time =  selectedHour + ":" + selectedMinute;
//            }
//
//        }
//        Log.i("ccccccccccccc", time);


//        Home myFragment = new Home();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.homeFrgmantLayout, myFragment, "myFragment");
//        fragmentTransaction.commit();

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        initComponents();
        addListeners();
    }

    private void handleAlarmOperation() {
        //medicineAdapter = new MedicineAdapter(this);
        //medicines = new ArrayList<Medicine>();
        int id = 0;
        medicines = medicineAdapter.selectAllMedicines();
        if (medicines != null)
            for (Medicine medicine : medicines) {
                id++;
                String medicineName = medicine.getMedicineName();
//                String time = DateFormat.format("hh:mm:ss", medicine.getMedicineTime()).toString();
                String time = medicine.getMedicineTime();
                String stringDate = medicine.getMedicineDate();
                int medicineRepitions = medicine.getMedicineRepition();
                Log.i("*** Time before Parsing",time);
                int hour = Integer.parseInt(time.substring(0, 2));
                Log.i("*** Time before Parsing",hour+"");
                int minute = Integer.parseInt(time.substring(3, 5));
                Log.i("*** Time before Parsing",minute+"");

                Date date = new Date(stringDate);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);

                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int week = calendar.get(Calendar.WEEK_OF_YEAR);

                Log.i("**********************", "");
                Log.i("*****Medicine Name****", medicineName);
                Log.i("****Year******", year + "");
                Log.i("****Month******", month + "");
                Log.i("****Day******", day + "");
                Log.i("****Time******", time);
                Log.i("****StringDate****", stringDate);
                Log.i("****int hour******", "" + hour);
                Log.i("****int minute******", "" + minute);
                Log.i("*****Repitions*******", "" + medicineRepitions);
                long interval = (24 / medicineRepitions) * 60 * 60 * 1000;
                Log.i("*******INTERVAL*******",interval+"");
                Log.i("*****ID*******", "" + id);
                Log.i("********************", "*******************");

                /***********************Start making the alarm*************************************/


                setModifiedAlarm(calendar, hour, minute, id, "Time to take medicine", interval);


            }
    }

    private void addListeners() {
//        lvHomeMedicinesList.setOnItemClickListener(this);
        fab.setOnClickListener(this);
    }

    private void initComponents() {


        lvHomeMedicinesList = (ListView) findViewById(R.id.lvHomeMedicinesList);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        medicines = new ArrayList<>();
        medicineAdapter = new MedicineAdapter(getApplicationContext());
        medicines = medicineAdapter.selectAllMedicines();
        List<String> medicinesName = new ArrayList<>();

        ArrayList<String> medicinesNameList = new ArrayList<>();
        ArrayList<Bitmap> medicinesImagesList = new ArrayList<>();
        final ArrayList<Integer> medicinesIdList = new ArrayList<>();
        if (medicines != null) {
            for (Medicine m : medicines) {
                medicinesNameList.add(m.getMedicineName());
                if (m.getMedicineImage() != null) {
                    medicinesImagesList.add(DbBitmapUtil.getImage(m.getMedicineImage()));
                }
                medicinesIdList.add(m.getMedicineId());
            }

            final MedicinesListArrayAdapter medicinesListArrayAdapter = new MedicinesListArrayAdapter(getApplicationContext(), medicinesNameList, medicinesImagesList, medicinesIdList);//,titleValues,images
            lvHomeMedicinesList.setAdapter(medicinesListArrayAdapter);
            lvHomeMedicinesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tvMedicineId = (TextView) view.findViewById(R.id.tvSingleListMedicineId);
                    int medicineId = Integer.parseInt(tvMedicineId.getText().toString());
                    Log.i("******ID:******", medicineId + "");
                    Intent editMedicine = new Intent(HomeActivity.this, EditMedicine.class);
                    editMedicine.putExtra("MedicineId", medicineId);
                    startActivity(editMedicine);
                }
            });

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                Intent addMedicineIntent = new Intent(this, AddMedicine.class);
                startActivity(addMedicineIntent);
                break;
        }
    }

    //************************** Alarm Method ******************************************************
    //This is the alarm method that takes Alarm properties
    public void setModifiedAlarm(Calendar myCalendar, int hour, int minute, int id, String message, long interval) {
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        int marker = AlarmUtils.getTimeMarker(hour);
//        in our MediReminder this is the calendar that will be retrieved from the database
//        Calendar c = Calendar.getInstance();
        Calendar c = myCalendar;
        /*if (marker == 0) {
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, 0);
            Log.e("AM/PM Marker", "AM " + hour);
        } else if (hour == 12) {
            c.set(Calendar.HOUR_OF_DAY, hour);
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, 0);
            Log.e("AM/PM Marker", "Dhuhr " + hour);
        } else {
            c.set(Calendar.HOUR_OF_DAY, (hour + 12));
            c.set(Calendar.MINUTE, minute);
            c.set(Calendar.SECOND, 0);
            Log.e("AM/PM Marker", "PM " + (hour + 12));
            //c.set(Calendar.AM_PM, marker);
        }*/
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        Calendar now = Calendar.getInstance();

        if (c.before(now)) {// if its in the past increment
            c.add(Calendar.DATE, 1);
            // c.set(Calendar.DATE, 1);
        }
        /*now.set(Calendar.YEAR, 2016);
        now.set(Calendar.MONTH, 4);
        now.set(Calendar.DATE, 1);
        now.set(Calendar.HOUR_OF_DAY, 11);
        now.set(Calendar.MINUTE, 55);
        now.set(Calendar.AM_PM, 0);*/

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Intent intent = new Intent(getBaseContext(), MedicineAlarmReceiver.class);
        intent.putExtra("notificationId", id);

        intent.putExtra("notificationMessage", message);
//        intent.putExtra("Image", bitmap);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), id, intent, 0);

        Log.i(message, c.toString() + "");
        Log.i("TIME", "Hour: " + hour
                + " Minute : " + minute
                + " Matrker: " + marker);

        Log.i("Now", now.getTimeInMillis() + "");
        Log.i("Alarm Calendar", c.getTimeInMillis() + "");
        Log.i("Alarm Calendar Right", c.get(Calendar.HOUR_OF_DAY) + " |Minute:"+c.get(Calendar.MINUTE));
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(),
                interval, pendingIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
        handleAlarmOperation();
        initComponents();
        addListeners();
    }
}
