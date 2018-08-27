package com.example.seyedamirhoseinhoseini.map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.zip.Inflater;

/**
 * Created by SeyedAmirhoseinHoseini on 8/26/18.
 */

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    Context context;
    ViewGroup parent;
    TextView title;
    TextView description;

    public MyInfoWindowAdapter(Context c, ViewGroup parent) {
        context = c;
        this.parent = parent;

    }

    @Override
    public View getInfoWindow(Marker marker) {
    /*    View v = LayoutInflater.from(context).inflate(R.layout.info_window, parent);
        title = v.findViewById(R.id.info_window_title);
        description = v.findViewById(R.id.info_window_description);
        title.setText(marker.getTitle());
        description.setText(marker.getSnippet());*/
        return null;

    }

    @Override
    public View getInfoContents(Marker marker) {
       View v = LayoutInflater.from(context).inflate(R.layout.info_window, parent);
        title = v.findViewById(R.id.info_window_title);
        description = v.findViewById(R.id.info_window_description);
        title.setText(marker.getTitle());
        description.setText(marker.getSnippet());
        return v;
    }
}
