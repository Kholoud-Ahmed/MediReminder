package com.iti.www.medireminder.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.iti.www.medireminder.R;
import com.iti.www.medireminder.adapter.MedicineAdapter;
import com.iti.www.medireminder.dto.Medicine;
import com.iti.www.medireminder.listadapters.MedicinesListArrayAdapter;
import com.iti.www.medireminder.utils.DbBitmapUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AlarmTrigger extends Activity implements View.OnClickListener{

    Button bAlarmTriggerStop;
    Button bAlarmTriggerSnooze;
    MediaPlayer mMediaPlayer;
    ListView lvAlarmTriggerMedicines;
    MedicineAdapter medicineAdapter;
    List<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_trigger);
        triggerAlarmSound();
        initComponents();
    }

    private void initComponents() {
        bAlarmTriggerStop = (Button) findViewById(R.id.bAlarmTriggerStop);
        bAlarmTriggerSnooze = (Button) findViewById(R.id.bAlarmTriggerSnooze);
        lvAlarmTriggerMedicines = (ListView) findViewById(R.id.lvAlarmTriggerMedicines);


        medicines = new ArrayList<>();
        medicineAdapter = new MedicineAdapter(getApplicationContext());

        Calendar calendar = Calendar.getInstance();
        int selectedHour = calendar.get(Calendar.HOUR_OF_DAY);
        int selectedMinute = calendar.get(Calendar.MINUTE);
        String currentTime=selectedHour+":"+selectedMinute;
        if(selectedHour<10){
            if(selectedMinute  <10){
                currentTime = "0" + selectedHour + ":0" + selectedMinute;
            }else{
                currentTime = "0" + selectedHour + ":" + selectedMinute;
            }

        }else if(selectedHour == 00){
            if(selectedMinute  <10){
                currentTime = "0" + selectedHour + ":0" + selectedMinute;
            }else{
                currentTime = "0" + selectedHour + ":" + selectedMinute;
            }
        }else{
            if(selectedMinute<10){
                currentTime =selectedHour + ":0" + selectedMinute;
            }else{
                currentTime =  selectedHour + ":" + selectedMinute;
            }
        }
        String currentDate = (String) DateFormat.format("M/dd/yyyy", new Date());
        Log.i("ccccccccccccc", currentTime);
        Log.i("DDDDDDDDD",currentDate);

        medicines = medicineAdapter.selectMedicinesByTimeAndDate(currentDate,currentTime);//////////////////////////////////////
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
            lvAlarmTriggerMedicines.setAdapter(medicinesListArrayAdapter);
            lvAlarmTriggerMedicines.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView tvMedicineId = (TextView) view.findViewById(R.id.tvSingleAlarmListMedicineId);
                    int medicineId = Integer.parseInt(tvMedicineId.getText().toString());
                    Log.i("******ID:******", medicineId + "");
                    Intent editMedicine = new Intent(AlarmTrigger.this, EditMedicine.class);
                    editMedicine.putExtra("MedicineId", medicineId);
                    startActivity(editMedicine);
                }
            });

        }
    }

    private void triggerAlarmSound() {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(AlarmTrigger.this,R.raw.ring);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mMediaPlayer.setLooping(true);
        mMediaPlayer.start();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bAlarmTriggerStop:
                this.finish();
                super.finish();
                break;

            case R.id.bAlarmTriggerSnooze:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initComponents();
    }
}
