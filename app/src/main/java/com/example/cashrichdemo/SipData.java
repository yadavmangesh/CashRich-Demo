package com.example.cashrichdemo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SipData {

@SerializedName("date")
@Expose
private String date;
@SerializedName("sensex")
@Expose
private String sensex;
@SerializedName("equity")
@Expose
private String equity;
@SerializedName("point")
@Expose
private String point;

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

public String getSensex() {
return sensex;
}

public void setSensex(String sensex) {
this.sensex = sensex;
}

public String getEquity() {
return equity;
}

public void setEquity(String equity) {
this.equity = equity;
}

public String getPoint() {
return point;
}

public void setPoint(String point) {
this.point = point;
}

    @Override
    public String toString() {
        return "SipData{" +
                "date='" + date + '\'' +
                ", sensex='" + sensex + '\'' +
                ", equity='" + equity + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}