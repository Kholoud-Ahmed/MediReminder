package com.iti.www.medireminder.dto;

/**
 * Created by KHoloud on 4/12/2016.
 */
public class Medicine {

    private int medicineId;
    private String medicineName;
    private byte[] medicineImage;
    private int medicineDose;
    private String medicineType;
    private String medicineDate;
    private String medicineTime;
    private int medicineRepition;

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public byte[] getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(byte[] medicineImage) {
        this.medicineImage = medicineImage;
    }

    public int getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(int medicineDose) {
        this.medicineDose = medicineDose;
    }

    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        this.medicineType = medicineType;
    }

    public String getMedicineDate() {
        return medicineDate;
    }

    public void setMedicineDate(String medicineDate) {
        this.medicineDate = medicineDate;
    }

    public String getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(String medicineTime) {
        this.medicineTime = medicineTime;
    }

    public int getMedicineRepition() {
        return medicineRepition;
    }

    public void setMedicineRepition(int medicineRepition) {
        this.medicineRepition = medicineRepition;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "medicineId=" + medicineId +
                ", medicineName='" + medicineName + '\'' +
                ", medicineImage=" + medicineImage +
                ", medicineDose=" + medicineDose +
                ", medicineType='" + medicineType + '\'' +
                ", medicineDate='" + medicineDate + '\'' +
                ", medicineTime=" + medicineTime +
                ", medicineRepition=" + medicineRepition +
                '}';
    }
}
