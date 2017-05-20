package com.example.diego.rotas.mapa.rota;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Locale;

/**
 * Created by DBlac on 19/05/2017.
 */

public class RotaAsyncTask extends AsyncTask<Double, Void, Void>{
    private ProgressDialog dialog;
    private GoogleMap mapView;
    private Context context;
    private Route route;


    public RotaAsyncTask(Context context, GoogleMap mapView){
        this.mapView = mapView;
        this.context = context;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Aguarde", "Calculando Rota");
    }

    @Override
    protected Void doInBackground(Double... params) {

        route = directions(new LatLng(params[0], params[1]), new LatLng(params[2], params[3]));
        return null;
    }

    protected void onPostExecute(Void result){
        super.onPostExecute(result);
        PolylineOptions options = new PolylineOptions().width(5).color(Color.RED).visible(true);

        for(LatLng latLng : route.getPoints()){
            options.add(latLng);
        }

        mapView.addPolyline(options);
        dialog.dismiss();
    }

    private Route directions(final LatLng start, final LatLng destiny){
        String urlRota = String.format(Locale.US,
                "http://maps.googleapis.com/maps/api/"+
                "directions/json?origin=%f,%f&"+
                "destination=%f,%f&" +
                "sensor=true&mode=driving",
                start.latitude,
                start.longitude,
                destiny.latitude,
                destiny.longitude);

        GoogleParser parser;
        parser = new GoogleParser(urlRota);
        return parser.parse();
    }
}
