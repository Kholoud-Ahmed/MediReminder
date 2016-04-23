package com.iti.www.medireminder.adapter;

/**
 * Created by KHoloud on 4/12/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iti.www.medireminder.database.Helper;
import com.iti.www.medireminder.dto.Medicine;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter {

    Context context;
    Helper dbHelper;
    SQLiteDatabase db;
    public MedicineAdapter(Context context) {
        this.context = context;
        dbHelper = new Helper(context);
        db = dbHelper.getWritableDatabase();
    }




    public void insertMedicine(Medicine medicine){
        ContentValues contentValues = new ContentValues();
       // contentValues.put(dbHelper.medicineId,medicine.getMedicineId());
        contentValues.put(dbHelper.medicineName, medicine.getMedicineName());
        contentValues.put(dbHelper.medicineImage, medicine.getMedicineImage());
        contentValues.put(dbHelper.medicineDose, medicine.getMedicineDose());
        contentValues.put(dbHelper.medicineType,medicine.getMedicineType());
        contentValues.put(dbHelper.medicineDate, medicine.getMedicineDate());
        contentValues.put(dbHelper.medicineTime, medicine.getMedicineTime());
        contentValues.put(dbHelper.medicineRepition, medicine.getMedicineRepition());

        db.insert(dbHelper.tableName, null, contentValues);
    }

    public Medicine selectMedicine(String medicineName){
        String[] coloumns = {dbHelper.medicineId,
                dbHelper.medicineName,
                dbHelper.medicineImage,
                dbHelper.medicineDose,
                dbHelper.medicineType,
                dbHelper.medicineDate,
                dbHelper.medicineRepition
        };

        Medicine retrivedMedicine = new Medicine();
        Cursor c ;
        String query = "Select "+dbHelper.medicineId + " , "+
                dbHelper.medicineName+" , "+
                dbHelper.medicineImage+" , "+
                dbHelper.medicineDose+" , "+
                dbHelper.medicineType+" , "+
                dbHelper.medicineDate+" , "+
                dbHelper.medicineTime+" , "+
                dbHelper.medicineRepition +
                " from "+dbHelper.tableName +
                " where " +dbHelper.medicineName + "='" + medicineName+"'";

        c =  db.rawQuery(query, null);
        if(c != null){
            if(c.moveToFirst()) {
                retrivedMedicine.setMedicineId(c.getInt(c.getColumnIndex(dbHelper.medicineId)));
                retrivedMedicine.setMedicineName(c.getString(c.getColumnIndex(dbHelper.medicineName)));
                retrivedMedicine.setMedicineImage(c.getBlob(c.getColumnIndex(dbHelper.medicineImage)));
                retrivedMedicine.setMedicineDose(c.getInt(c.getColumnIndex(dbHelper.medicineDose)));
                retrivedMedicine.setMedicineType(c.getString(c.getColumnIndex(dbHelper.medicineType)));
                retrivedMedicine.setMedicineDate(c.getString(c.getColumnIndex(dbHelper.medicineDate)));
                retrivedMedicine.setMedicineTime(c.getString(c.getColumnIndex(dbHelper.medicineTime)));
                retrivedMedicine.setMedicineRepition(c.getInt(c.getColumnIndex(dbHelper.medicineRepition)));
            }
        }else{
            return null;
        }
        return retrivedMedicine;
    }

    public Medicine selectMedicineById(int medicineId){
        String[] coloumns = {dbHelper.medicineId,
                dbHelper.medicineName,
                dbHelper.medicineImage,
                dbHelper.medicineDose,
                dbHelper.medicineType,
                dbHelper.medicineDate,
                dbHelper.medicineRepition
        };

        Medicine retrivedMedicine = new Medicine();
        Cursor c ;
        String query = "Select "+dbHelper.medicineId + " , "+
                dbHelper.medicineName+" , "+
                dbHelper.medicineImage+" , "+
                dbHelper.medicineDose+" , "+
                dbHelper.medicineType+" , "+
                dbHelper.medicineDate+" , "+
                dbHelper.medicineTime+" , "+
                dbHelper.medicineRepition +
                " from "+dbHelper.tableName +
                " where " +dbHelper.medicineId + "='" + medicineId+"'";

        c =  db.rawQuery(query, null);
        if(c != null){
            if(c.moveToFirst()) {
                retrivedMedicine.setMedicineId(c.getInt(c.getColumnIndex(dbHelper.medicineId)));
                retrivedMedicine.setMedicineName(c.getString(c.getColumnIndex(dbHelper.medicineName)));
                retrivedMedicine.setMedicineImage(c.getBlob(c.getColumnIndex(dbHelper.medicineImage)));
                retrivedMedicine.setMedicineDose(c.getInt(c.getColumnIndex(dbHelper.medicineDose)));
                retrivedMedicine.setMedicineType(c.getString(c.getColumnIndex(dbHelper.medicineType)));
                retrivedMedicine.setMedicineDate(c.getString(c.getColumnIndex(dbHelper.medicineDate)));
                retrivedMedicine.setMedicineTime(c.getString(c.getColumnIndex(dbHelper.medicineTime)));
                retrivedMedicine.setMedicineRepition(c.getInt(c.getColumnIndex(dbHelper.medicineRepition)));
            }
        }else{
            return null;
        }
        return retrivedMedicine;
    }

    public List<Medicine> selectAllMedicines(){
        String[] coloumns = {dbHelper.medicineId,
                dbHelper.medicineName,
                dbHelper.medicineImage,
                dbHelper.medicineDose,
                dbHelper.medicineType,
                dbHelper.medicineDate,
                dbHelper.medicineRepition
        };

        List<Medicine> medicines = new ArrayList<>();
        Cursor c ;
        String query = "Select "+dbHelper.medicineId + " , "+
                dbHelper.medicineName+" , "+
                dbHelper.medicineImage+" , "+
                dbHelper.medicineDose+" , "+
                dbHelper.medicineType+" , "+
                dbHelper.medicineDate+" , "+
                dbHelper.medicineTime+" , "+
                dbHelper.medicineRepition +
                " from "+dbHelper.tableName;

        c =  db.rawQuery(query, null);
        while (c.moveToNext()){
            Medicine retrivedMedicine = new Medicine();
            retrivedMedicine.setMedicineId(c.getInt(c.getColumnIndex(dbHelper.medicineId)));
            retrivedMedicine.setMedicineName(c.getString(c.getColumnIndex(dbHelper.medicineName)));
            retrivedMedicine.setMedicineImage(c.getBlob(c.getColumnIndex(dbHelper.medicineImage)));
            retrivedMedicine.setMedicineDose(c.getInt(c.getColumnIndex(dbHelper.medicineDose)));
            retrivedMedicine.setMedicineType(c.getString(c.getColumnIndex(dbHelper.medicineType)));
            retrivedMedicine.setMedicineDate(c.getString(c.getColumnIndex(dbHelper.medicineDate)));
            retrivedMedicine.setMedicineTime(c.getString(c.getColumnIndex(dbHelper.medicineTime)));
            retrivedMedicine.setMedicineRepition(c.getInt(c.getColumnIndex(dbHelper.medicineRepition)));

            medicines.add(retrivedMedicine);

        }
        if(medicines.size() ==0){
            return null;
        }
        return medicines;
    }

    public void updateMedicine(Medicine medicine){
        ContentValues contentValues = new ContentValues();
//        contentValues.put(dbHelper.medicineId,medicine.getMedicineId());
        contentValues.put(dbHelper.medicineName, medicine.getMedicineName());
        contentValues.put(dbHelper.medicineImage, medicine.getMedicineImage());
        contentValues.put(dbHelper.medicineDose, medicine.getMedicineDose());
        contentValues.put(dbHelper.medicineType,medicine.getMedicineType());
        contentValues.put(dbHelper.medicineDate, medicine.getMedicineDate());
        contentValues.put(dbHelper.medicineTime, medicine.getMedicineTime());
        contentValues.put(dbHelper.medicineRepition, medicine.getMedicineRepition());

        db.update(dbHelper.tableName, contentValues,  dbHelper.medicineId +"='"+medicine.getMedicineId()+"'", null);
    }

    public boolean deleteMedicine(int medicineId){
        return db.delete(dbHelper.tableName, dbHelper.medicineId + "=" + medicineId, null) > 0;
    }


    public List<Medicine> selectMedicinesByTimeAndDate(String date, String time){
        String[] coloumns = {dbHelper.medicineId,
                dbHelper.medicineName,
                dbHelper.medicineImage,
                dbHelper.medicineDose,
                dbHelper.medicineType,
                dbHelper.medicineDate,
                dbHelper.medicineRepition
        };

        List<Medicine> medicines = new ArrayList<>();
        Cursor c ;
        String query = "Select "+dbHelper.medicineId + " , "+
                dbHelper.medicineName+" , "+
                dbHelper.medicineImage+" , "+
                dbHelper.medicineDose+" , "+
                dbHelper.medicineType+" , "+
                dbHelper.medicineDate+" , "+
                dbHelper.medicineTime+" , "+
                dbHelper.medicineRepition +
                " from "+dbHelper.tableName+
                " where "+dbHelper.medicineDate+ "='" + date+"' and "+dbHelper.medicineTime + "='" + time+"'";

        c =  db.rawQuery(query, null);
        while (c.moveToNext()){
            Medicine retrivedMedicine = new Medicine();
            retrivedMedicine.setMedicineId(c.getInt(c.getColumnIndex(dbHelper.medicineId)));
            retrivedMedicine.setMedicineName(c.getString(c.getColumnIndex(dbHelper.medicineName)));
            retrivedMedicine.setMedicineImage(c.getBlob(c.getColumnIndex(dbHelper.medicineImage)));
            retrivedMedicine.setMedicineDose(c.getInt(c.getColumnIndex(dbHelper.medicineDose)));
            retrivedMedicine.setMedicineType(c.getString(c.getColumnIndex(dbHelper.medicineType)));
            retrivedMedicine.setMedicineDate(c.getString(c.getColumnIndex(dbHelper.medicineDate)));
            retrivedMedicine.setMedicineTime(c.getString(c.getColumnIndex(dbHelper.medicineTime)));
            retrivedMedicine.setMedicineRepition(c.getInt(c.getColumnIndex(dbHelper.medicineRepition)));

            medicines.add(retrivedMedicine);

        }
        if(medicines.size() ==0){
            return null;
        }
        return medicines;
    }


    public Medicine selectMedicinesByNameAndType(String name, String type){
        String[] coloumns = {dbHelper.medicineId,
                dbHelper.medicineName,
                dbHelper.medicineImage,
                dbHelper.medicineDose,
                dbHelper.medicineType,
                dbHelper.medicineDate,
                dbHelper.medicineRepition
        };

        Cursor c ;
        Medicine retrivedMedicine = new Medicine();
        String query = "Select "+dbHelper.medicineId + " , "+
                dbHelper.medicineName+" , "+
                dbHelper.medicineImage+" , "+
                dbHelper.medicineDose+" , "+
                dbHelper.medicineType+" , "+
                dbHelper.medicineDate+" , "+
                dbHelper.medicineTime+" , "+
                dbHelper.medicineRepition +
                " from "+dbHelper.tableName+
                " where "+dbHelper.medicineName+ "='" + name+"' and "+dbHelper.medicineType + "='" + type+"'";

        c =  db.rawQuery(query, null);
        while (c.moveToNext()){
            retrivedMedicine = new Medicine();
            retrivedMedicine.setMedicineId(c.getInt(c.getColumnIndex(dbHelper.medicineId)));
            retrivedMedicine.setMedicineName(c.getString(c.getColumnIndex(dbHelper.medicineName)));
            retrivedMedicine.setMedicineImage(c.getBlob(c.getColumnIndex(dbHelper.medicineImage)));
            retrivedMedicine.setMedicineDose(c.getInt(c.getColumnIndex(dbHelper.medicineDose)));
            retrivedMedicine.setMedicineType(c.getString(c.getColumnIndex(dbHelper.medicineType)));
            retrivedMedicine.setMedicineDate(c.getString(c.getColumnIndex(dbHelper.medicineDate)));
            retrivedMedicine.setMedicineTime(c.getString(c.getColumnIndex(dbHelper.medicineTime)));
            retrivedMedicine.setMedicineRepition(c.getInt(c.getColumnIndex(dbHelper.medicineRepition)));


        }
        return retrivedMedicine;
    }

}
