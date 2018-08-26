package com.example.seyedamirhoseinhoseini.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.zip.Inflater;

/**
 * Created by SeyedAmirhoseinHoseini on 8/26/18.
 */

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    ViewGroup parent;

    public MyInfoWindowAdapter(Context c, ViewGroup parent) {
        context = c;
        this.parent = parent;

    }

    @Override
    public View getInfoWindow(Marker marker) {
        return LayoutInflater.from(context).inflate(R.layout.info_window, parent);
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
