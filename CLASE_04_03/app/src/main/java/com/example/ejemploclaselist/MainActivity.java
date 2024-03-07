package com.example.ejemploclaselist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ListView listaView;
    public ArrayList<producto> Listproducto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaView = findViewById(R.id.lista);
        Listproducto = new ArrayList<producto>();
        rellenarListView();
        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                producto objetoProducto = Listproducto.get(i);
                Toast.makeText(MainActivity.this, "Producto -> "+objetoProducto.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void rellenarListView(){
        Listproducto.add(new producto("Agua",1.00));
        Listproducto.add(new producto("Coca Cola",0.75));
        Listproducto.add(new producto("Pepsi",0.80));
        AdaptadorLista adaptador = new AdaptadorLista(MainActivity.this, Listproducto,R.layout.itemlist);
        listaView.setAdapter(adaptador);
    }
}