package com.iti.www.medireminder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KHoloud on 4/12/2016.
 */
public class Helper extends SQLiteOpenHelper {

    public final static String tableName = "Medicine";
    public final static  String databaseName = "MedicalReminder";
    public final static  int databaseVersion = 1;
    public String medicineId = "medicineId";
    public String medicineName  = "medicineName";
    public String medicineImage = "medicineImage";
    public String medicineDose = "medicineDose";
    public String medicineType = "medicineType";
    public String medicineDate = "medicineDate";
    public String medicineTime = "medicineTime";
    public String medicineRepition = "medicineRepition";

    public Helper(Context context) {
        super(context,databaseName,null,databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table "+tableName+"(" + medicineId +" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                medicineName + " varchar(60),"+
                medicineImage + " blob, "+
                medicineDose + " Int, "+
                medicineType + " varchar(45), "+
                medicineDate + " varchar(45), "+
                medicineTime + " varchar(45), "+
                medicineRepition + " Int"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}
