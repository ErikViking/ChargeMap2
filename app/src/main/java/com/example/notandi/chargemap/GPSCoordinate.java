package com.example.notandi.chargemap;


import java.io.Serializable;

/**
 * Created by notandi on 09.11.14.
 */
public class GPSCoordinate implements Serializable {


    public double lat;
    public double lon;
    public double distance;
    //public double clearlineDist;
    //public double

    public GPSCoordinate(double lat, double lon) {
        super();

        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

}
