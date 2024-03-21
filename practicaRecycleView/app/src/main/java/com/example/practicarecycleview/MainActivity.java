package com.example.practicarecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Button eliminar;
    public ArrayList<Producto> dataProducto;
    public RecyclerView listaProducto;
    public ProductoAdapter productoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eliminar = findViewById(R.id.btnInsertar);
        listaProducto = findViewById(R.id.listaRecycler);
        dataProducto = new ArrayList<Producto>();

        dataProducto.add(new Producto("Pizza",5.50));
        dataProducto.add((new Producto("Coca Cola",0.75)));

        productoAdapter = new ProductoAdapter(dataProducto,this);
        listaProducto.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        listaProducto.setAdapter(productoAdapter);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataProducto.add(new Producto("Little Pizza",5.0));
                productoAdapter.notifyDataSetChanged();
            }
        });
    }
}