package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public Spinner spCategorias;
    public ArrayList<Categoria> listCategoria;
    public EditText txtNombre, txtPrecio, txtCantidad;
    public double total;
    public ArrayList<Productos> listProducto;
    public ProductoAdapter adapter;
    public ListView listaDataProducto;
    public Button btnGuardar;
    public TextView totalCompra;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spCategorias = findViewById(R.id.spinner);
        listCategoria = new ArrayList<Categoria>();
        txtNombre = findViewById(R.id.editTextText);
        txtPrecio = findViewById(R.id.editTextNumber2);
        txtCantidad = findViewById(R.id.editTextNumber);
        btnGuardar = findViewById(R.id.button);
        listProducto = new ArrayList<Productos>();
        listaDataProducto = findViewById(R.id.vLista);
        totalCompra = findViewById(R.id.lblTotalCompra);
        rellenarSpinner();
        adapter = new ProductoAdapter(listProducto,this,this);
        calcularTotalCompra();
        listaDataProducto.setAdapter(adapter);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Productos objetoProducto = new Productos();
                objetoProducto.setNombreProducto(txtNombre.getText().toString());
                objetoProducto.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));
                objetoProducto.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
                total = (Integer.parseInt(txtCantidad.getText().toString()) * Double.parseDouble(txtPrecio.getText().toString()));
                objetoProducto.setTotal(total);
                objetoProducto.setCategoria((Categoria) spCategorias.getSelectedItem());

                listProducto.add(objetoProducto);
                adapter.notifyDataSetChanged();
                calcularTotalCompra();
            }
        });
    }
    public void rellenarSpinner(){
        listCategoria.add(new Categoria("Juguetes",R.drawable.iconocategoria1,R.color.categoriaJuguete));
        listCategoria.add(new Categoria("Salud",R.drawable.baseline_add_business_24,R.color.categoriaSalud));
        ArrayAdapter<Categoria> adapter = new ArrayAdapter<Categoria>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listCategoria);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spCategorias.setAdapter(adapter);
    }
    public void calcularTotalCompra(){
        double sumatoria =0;
        for (Productos itemProducto: listProducto) {
            sumatoria +=itemProducto.getTotal();
        }
        totalCompra.setText(String.valueOf(sumatoria));
    }
}