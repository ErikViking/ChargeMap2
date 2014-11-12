package com.example.notandi.chargemap;


/**
 * Created by notandi on 09.11.14.
 */
public class GPSCoordinate {


    public double lat;
    public double lon;
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
}
