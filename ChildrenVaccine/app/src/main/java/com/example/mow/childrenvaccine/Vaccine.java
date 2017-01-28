package com.example.mow.childrenvaccine;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mow on 6/25/2015.
 */
public class Vaccine implements Parcelable {
    private String diseaseName;
    private String vaccineName;
    private String details;

    private int vaccineID;

    public Vaccine(Parcel source) {

        vaccineID = source.readInt();
        diseaseName = source.readString();
        vaccineName = source.readString();
        details = source.readString();
    }

    public Vaccine() {

    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(int vaccineID) {
        this.vaccineID = vaccineID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {


        dest.writeInt(vaccineID);
        dest.writeString(diseaseName);
        dest.writeString(vaccineName);
        dest.writeString(details);

    }

    public static final Creator<Vaccine> CREATOR
            = new Parcelable.Creator<Vaccine>() {
        @Override
        public Vaccine createFromParcel(Parcel source) {
            return new Vaccine(source);
        }

        @Override
        public Vaccine[] newArray(int size) {
            return new Vaccine[size];
        }
    };
}

