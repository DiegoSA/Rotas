package com.example.diego.rotas.mapa;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


import com.example.diego.rotas.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int opcao;
    private ArrayList<Marker> entregas; //ainda estou em dúvida de como utilizar o armazenamento de marcadores
    private LocationManager location;
    private List<LatLng> decodePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        decodePath = null;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        /*location = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        location.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) MapsActivity.this);*/

        // defino a latitude e longitude do marcador e adiciono no mapa
        LatLng entrega1 = new LatLng(-7.12, -34.879);
        mMap.addMarker(new MarkerOptions().position(entrega1).title("Primeira Entrega"));
        LatLng entrega2 = new LatLng(-7.15, -34.8);
        mMap.addMarker(new MarkerOptions().position(entrega2).title("Segunda Entrega"));
        LatLng entrega3 = new LatLng(-7.13, -34.85);
        mMap.addMarker(new MarkerOptions().position(entrega3).title("Terceira Entrega"));

        /*decodePath.add(new LatLng(-7.12, -34.879));
        decodePath.add(new LatLng(-7.15, -34.8));
        decodePath.add(entrega3);

        mMap.addPolyline(new PolylineOptions().addAll(decodePath).color(Color.CYAN));*/

        //foco no primeiro marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(entrega1));

        //método para capturar o touch no marcador
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                AlertDialog.Builder ad = new AlertDialog.Builder(MapsActivity.this);
                ad.setTitle("Confirmação de Entrega");
                ad.setMessage("A Entrega foi Finalizada?");
                ad.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MapsActivity.this, "Entrega realizada", Toast.LENGTH_SHORT).show();
                        opcao = 1;
                        alterPosition(marker, opcao);
                    }
                });

                ad.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MapsActivity.this, "Entrega não realizada", Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder adNao = new AlertDialog.Builder(MapsActivity.this);
                        adNao.setTitle("Motivo da Não Entrega");
                        adNao.setMessage("Devolução ou Reentrega?");
                        adNao.setPositiveButton("Devolução", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                opcao = 2;
                                alterPosition(marker, opcao);
                            }
                        });
                        adNao.setNegativeButton("Reentrega", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                opcao = 3;
                                alterPosition(marker, opcao);
                            }
                        });
                        adNao.show();
                    }
                });

                ad.show();

                return false;
            }
        });

        //zoom no marcador focado
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));
    }

    public void alterPosition(Marker marker, int opcao){
        switch (opcao){
            case 1:
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                break;
            case 2:
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                break;
            case 3:
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                break;
            default:
                break;
        }
    }
}
