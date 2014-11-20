package com.example.notandi.chargemap;import android.app.Activity;import android.content.Intent;import android.os.Bundle;import android.util.Log;import android.view.View;import android.view.View.OnClickListener;import android.view.ViewGroup.LayoutParams;import android.widget.Button;import android.widget.LinearLayout;public class MenuActivity extends Activity implements OnClickListener {    Button buttonSQL, navigationButton, buttonGPSMenu, distanceListButton, distanceListButton2;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        LinearLayout linLayout = new LinearLayout(this);        linLayout.setOrientation(LinearLayout.VERTICAL);        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);        setContentView(linLayout, linLayoutParam);        buttonSQL = new Button(this);        buttonSQL.setText("SQL Administration");        buttonSQL.setOnClickListener(this);        linLayout.addView(buttonSQL);        navigationButton = new Button(this);        navigationButton.setText("Navigation");        navigationButton.setOnClickListener(this);        linLayout.addView(navigationButton);        buttonGPSMenu = new Button(this);        buttonGPSMenu.setText("GPS Menu");        buttonGPSMenu.setOnClickListener(this);        linLayout.addView(buttonGPSMenu);        distanceListButton = new Button(this);        distanceListButton.setText("Distance List");        distanceListButton.setOnClickListener(this);        linLayout.addView(distanceListButton);        distanceListButton2 = new Button(this);        distanceListButton2.setText("Close Stations List");        distanceListButton2.setOnClickListener(this);        linLayout.addView(distanceListButton2);    }    public void onClick(View v) {        Log.d("Default", "A button in MenuActivity was clicked");        if (v == buttonSQL) {            Intent i = new Intent(this, SQLMenu.class);            startActivity(i);        }        if (v == navigationButton) {            Intent i = new Intent(this, MapActivity.class);            startActivity(i);        }        if (v == buttonGPSMenu) {            Intent i = new Intent(this, GPSActivity.class);            startActivity(i);        }        if (v == distanceListButton) {            Intent i = new Intent(this, DistanceListActivity.class);            startActivity(i);        }        if (v == distanceListButton2) {            Intent i = new Intent(this, DistanceListActivity3.class);            startActivity(i);        }    }}