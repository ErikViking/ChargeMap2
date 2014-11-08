package com.example.notandi.chargemap;

import android.app.Activity;

/**
 * Created by notandi on 08.11.14.
 */
public class DistanceListActivity extends Activity {

    DBConnect db = new DBConnect(this);
    double[][] list = db.getList();






}


