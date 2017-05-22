package com.example.diego.rotas.mapa;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


import com.example.diego.rotas.R;
import com.example.diego.rotas.banco.DBController;
import com.example.diego.rotas.mapa.rota.RotaAsyncTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int opcao;
    private ArrayList<Marker> entregas; //ainda estou em dúvida de como utilizar o armazenamento de marcadores
    private LocationManager location;
    private List<LatLng> pontos;
    private DBController dbController;
    private Cursor cursor;
    private String endereco;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        dbController = new DBController(getBaseContext());
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

        LatLng entrega, entrega1 = null;
        cursor = dbController.listarPedidos();
        int i = 1;
        do{
            endereco = (cursor.getString(cursor.getColumnIndexOrThrow("endereco"))) + ", " + (cursor.getString(cursor.getColumnIndexOrThrow("numero")));
            entrega = buscarCoordenadasEndereco(endereco);
            mMap.addMarker(new MarkerOptions().position(entrega).title(i + "ª Entrega")).setTag(i + "ª entrega");
            if(i == 1){
                entrega1 = entrega;
            }
            pontos.add(i-1,entrega);
            i++;
        }while (cursor.moveToNext());

        /*LatLng entrega1 = new LatLng(-7.12, -34.879);
        mMap.addMarker(new MarkerOptions().position(entrega1).title("Primeira Entrega")).setTag("Primeira Entrega");
        LatLng entrega2 = new LatLng(-7.15, -34.8);
        mMap.addMarker(new MarkerOptions().position(entrega2).title("Segunda Entrega"));
        LatLng entrega3 = new LatLng(-7.13, -34.85);
        mMap.addMarker(new MarkerOptions().position(entrega3).title("Terceira Entrega"));

        makeURL(entrega1.latitude, entrega1.longitude, entrega2.latitude, entrega2.longitude);*/





        //foco no primeiro marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(entrega1));

        //método para capturar o touch no marcador
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                String seq = (String) marker.getTag();
                AlertDialog.Builder ad = new AlertDialog.Builder(MapsActivity.this);
                ad.setTitle("Confirmação de Entrega");
                ad.setMessage("A "+ seq +"  foi Finalizada?");
                ad.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MapsActivity.this, "Entrega realizada", Toast.LENGTH_SHORT).show();
                        //dbController.finalizarEntrega(); //falta passar o numero do pedido por referência
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

        for(int j=0; j<(pontos.size() - 1); j++) {
            traçarRota(this, mMap, pontos.get(j), pontos.get(j+1));
        }
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

    public void traçarRota(Context context, GoogleMap mMap, LatLng start, LatLng destiny){
        new RotaAsyncTask(context, mMap).execute(start.latitude, start.longitude, destiny.latitude, destiny.longitude);
    }

    public String makeURL(double sourcelat, double sourcelog, double destlat, double destlog) {
        StringBuilder urlString = new StringBuilder();
        urlString.append("http://maps.googleapis.com/maps/api/directions/json");
        urlString.append("?origin=");// from
        urlString.append(Double.toString(sourcelat));
        urlString.append(",");
        urlString.append(Double.toString(sourcelog));
        urlString.append("&destination=");// to
        urlString.append(Double.toString(destlat));
        urlString.append(",");
        urlString.append(Double.toString(destlog));
        urlString.append("&sensor=false&mode=driving&alternatives=true");
        return urlString.toString();
    }

    public LatLng buscarCoordenadasEndereco(String enderecoOrigem) {
        Geocoder geoCoder = new Geocoder(this, Locale.getDefault());// esse Geocoder aqui é quem vai traduzir o endereço de String para coordenadas double
        List<Address> addresses = null; //este Adress aqui recebe um retorno do metodo geoCoder.getFromLocationName vc manipula este retorno pra pega as coordenadas
        LatLng latLng;
        try {
            addresses = geoCoder.getFromLocationName(enderecoOrigem, 1);// o numero um aqui é a quantidade maxima de resultados que vc quer receber
            double fromLat = addresses.get(0).getLatitude();
            double fromLon = addresses.get(0).getLongitude();
            latLng = new LatLng(fromLat, fromLon);

            return latLng;
            /*addresses = geoCoder.getFromLocationName(enderecoDestino, 1);
            double toLat = addresses.get(0).getLatitude();
            double toLon = addresses.get(0).getLongitude();*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}





