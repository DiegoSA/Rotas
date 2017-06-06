package com.example.diego.rotas.mapa;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private ArrayList<LatLng> pontos;
    private DBController dbController;
    private Cursor cursorPedido, cursorCliente;
    private String endereco;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        pontos = new ArrayList<LatLng>();
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

        LatLng entrega;
        cursorPedido = dbController.listarPedidos();
        cursorCliente = dbController.listarClientes();
        int i = 1;
        do{
            do{
                if(cursorPedido.getString(cursorPedido.getColumnIndexOrThrow("codigoCliente")).equals(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("codigo")))) {
                    endereco = (cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("endereco"))) + ", " + (cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("bairro"))) + ", " + (cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("cidade")));
                    entrega = buscarCoordenadasEndereco(endereco);
                    mMap.addMarker(new MarkerOptions().position(entrega).title(i + "ª Entrega")).setTag(cursorPedido.getString(cursorPedido.getColumnIndexOrThrow("numeroNota")));
                    pontos.add(entrega);
                }
            }while(cursorCliente.moveToNext());
            cursorCliente.moveToFirst();
            i++;
        }while (cursorPedido.moveToNext());

        //foco no primeiro marcador
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pontos.get(0)));

        //método para capturar o touch no marcador
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {

                String seq = (String) marker.getTag();
                AlertDialog.Builder ad = new AlertDialog.Builder(MapsActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View alert = inflater.inflate(R.layout.confirmacao_entrega, null);
                final Button end = (Button) alert.findViewById(R.id.buttonFinalizar);
                final Button noEnd = (Button) alert.findViewById(R.id.buttonDevolvida);
                final TextView numNota = (TextView) alert.findViewById(R.id.numeroNota);
                final TextView razao = (TextView) alert.findViewById(R.id.razaoSocial);
                final TextView fantasia = (TextView) alert.findViewById(R.id.fantasia);
                final TextView valor = (TextView) alert.findViewById(R.id.valor);
                final TextView vendedor = (TextView) alert.findViewById(R.id.vendedor);
                final TextView telefone = (TextView) alert.findViewById(R.id.telefone
                );
                cursorPedido.moveToFirst();
                do{
                    if(cursorPedido.getString(cursorPedido.getColumnIndexOrThrow("numeroNota")).equals(marker.getTag().toString())) {
                        do{
                            if(cursorPedido.getString(cursorPedido.getColumnIndexOrThrow("codigoCliente")).equals(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("codigo")))) {
                                numNota.setText(cursorPedido.getString(cursorPedido.getColumnIndexOrThrow("numeroNota")));
                                razao.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("razao")));
                                fantasia.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("fantasia")));
                                valor.setText("R$ 500,00");
                                vendedor.setText(cursorCliente.getString(cursorCliente.getColumnIndexOrThrow("codigoVendedor")));
                                telefone.setText("83 3235-4869");
                            }
                        }while(cursorCliente.moveToNext());
                        cursorCliente.moveToFirst();
                    }
                }while (cursorPedido.moveToNext());

                /*end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder adNao = new AlertDialog.Builder(MapsActivity.this);
                        adNao.setTitle("Confirmação de Entrega");
                        adNao.setMessage("Confirma a entrega?");
                        adNao.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MapsActivity.this, "Entrega realizada", Toast.LENGTH_SHORT).show();
                                opcao = 1;
                                alterPosition(marker, opcao);
                                dialog.dismiss();
                            }
                        });
                        adNao.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alterPosition(marker, opcao);
                                dialog.dismiss();
                            }
                        });
                        adNao.show();
                    }
                });

                noEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                });*/

                ad.setView(alert);

                /*ad.setTitle("Confirmação de Entrega");
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
                });*/

                ad.show();

                return false;
            }
        });

        //zoom no marcador focado
        mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        //traçar a rota entre todos os marcadores do mapa
        for(int j=0; j<(pontos.size() - 1); j++) {
            traçarRota(this, mMap, pontos.get(j), pontos.get(j+1));
        }
    }

    //altera a posição do marcador passado por referência
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

    //traça a rota de um ponto a outro
    public void traçarRota(Context context, GoogleMap mMap, LatLng start, LatLng destiny){
        new RotaAsyncTask(context, mMap).execute(start.latitude, start.longitude, destiny.latitude, destiny.longitude);
    }

    //transformar o endereço extenso em coordenadas
    public LatLng buscarCoordenadasEndereco(String enderecoOrigem) {
        Geocoder geoCoder = new Geocoder(this, Locale.US);
        List<Address> addresses = null;
        LatLng latLng;
        try {
            addresses = geoCoder.getFromLocationName(enderecoOrigem, 1);
            if(addresses.size()>0) {
                double fromLat = addresses.get(0).getLatitude();
                double fromLon = addresses.get(0).getLongitude();
                latLng = new LatLng(fromLat, fromLon);
                return latLng;
            }

            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}





